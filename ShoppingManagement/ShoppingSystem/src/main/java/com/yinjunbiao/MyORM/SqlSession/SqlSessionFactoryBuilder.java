package com.yinjunbiao.MyORM.SqlSession;

import com.yinjunbiao.MyORM.DataSource.JNDIDatasource;
import com.yinjunbiao.MyORM.DataSource.PooledDataSource;
import com.yinjunbiao.MyORM.DataSource.UnPooledDataSource;
import com.yinjunbiao.MyORM.MappedStatement.MappedStatement;
import com.yinjunbiao.MyORM.MappedStatement.ResultMap;
import com.yinjunbiao.MyORM.Transaction.JdbcTransaction;
import com.yinjunbiao.MyORM.Transaction.ManagedTransaction;
import com.yinjunbiao.MyORM.Transaction.Transaction;
import com.yinjunbiao.util.CONST;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SqlSessionFactory构造器对象
 * @author yinjunbiao
 * @version 1.0
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream){
        System.out.println(inputStream);
        SqlSessionFactory sqlSessionFactory = null;
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element environments = (Element) document.selectSingleNode("/configuration/environments");
            String defaultEnvironment = environments.attributeValue("default");
            Element environment = (Element) document.selectSingleNode("/configuration/environments/environment[@id='" + defaultEnvironment + "']");
            Element transactionManagerElt = environment.element("transactionManager");
            Element dataSourceElt = environment.element("dataSource");

            DataSource dataSource = getDataSource(dataSourceElt);
            Transaction transaction = getTransaction(transactionManagerElt,dataSource);

            List<Node> nodes = document.selectNodes("//mapper");
            List<String> sqlMapperPaths = new ArrayList<>();
            for (Node node : nodes) {
                Element element = (Element) node;
                String resource = element.attributeValue("resource");
                sqlMapperPaths.add(resource);
            }

            System.out.println(sqlMapperPaths);
            Map<String, MappedStatement> mappedStatementMaps = getMappedStatements(sqlMapperPaths);

            Map<String , ResultMap>maps = getResultMap(sqlMapperPaths);

            sqlSessionFactory = new SqlSessionFactory(transaction,mappedStatementMaps,maps);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

    private Map<String, ResultMap> getResultMap(List<String> sqlMapperPaths) {
        Map<String ,ResultMap> resultMaps = new HashMap<>();
        for (String sqlMapperPath : sqlMapperPaths) {
            try {
                SAXReader reader = new SAXReader();
                Document document = reader.read(SqlSessionFactoryBuilder.class.getResourceAsStream(sqlMapperPath));
                Element mapper = (Element) document.selectSingleNode("mapper");

                List<Element> elements = mapper.elements("resultMap");
                for (Element element : elements) {
                    String id = element.attributeValue("id");
                    String type = element.attributeValue("type");
                    List<Element> maps = element.elements();
                    Map<String ,String > propertyMap = new HashMap<>();
                    for (Element map : maps) {
                        String property = map.attributeValue("property");
                        String column = map.attributeValue("column");
                        propertyMap.put(property,column);
                    }
                    resultMaps.put(id,new ResultMap(type,propertyMap));
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        return resultMaps;
    }

    private Map<String, MappedStatement> getMappedStatements(List<String> sqlMapperPaths) {
        Map<String, MappedStatement> mappedStatementMaps = new HashMap<>();
        for (String sqlMapperPath : sqlMapperPaths) {

            try {
                SAXReader reader = new SAXReader();
                Document document = reader.read(SqlSessionFactoryBuilder.class.getResourceAsStream(sqlMapperPath));
                Element mapper = (Element) document.selectSingleNode("mapper");
                String namespace = mapper.attributeValue("namespace");


                List<Element> elements = mapper.elements();
                for (Element element : elements) {

                    String id = element.attributeValue("id");
                    String resultType = element.attributeValue("resultType");
                    String sqlId = namespace + "." + id;
                    String sql = element.getTextTrim();
                    String resultMap = element.attributeValue("resultMap");
                    MappedStatement mappedStatement = new MappedStatement(sql,resultType,resultMap);
                    mappedStatementMaps.put(sqlId,mappedStatement);
                }

            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        System.out.println("mappedStatement="+mappedStatementMaps);
        return mappedStatementMaps;
    }

    private Transaction getTransaction(Element transactionManagerElt, DataSource dataSource) {
        Transaction transaction = null;
        String type = transactionManagerElt.attributeValue("type");
        if (CONST.MANAGED_TRANSACTION.equals(type)){
            transaction = new ManagedTransaction();
        }
        if (CONST.JDBC_TRANSACTION.equals(type)){
            transaction = new JdbcTransaction(dataSource,false);
        }
        return transaction;
    }

    private DataSource getDataSource(Element dataSourceElt) {
        String type = dataSourceElt.attributeValue("type");
        String poolName = dataSourceElt.attributeValue("poolName");
        DataSource dataSource = null;
        Map<String,String> maps = new HashMap<>();
        List<Element> elements = dataSourceElt.elements();
        for (Element element : elements) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            maps.put(name,value);
        }
        if (CONST.JNDI_DATASOURCE.equals(type)){
            dataSource = new JNDIDatasource();
        }
        if (CONST.UN_POOLED_DATASOURCE.equals(type)){
            dataSource = new UnPooledDataSource(maps.get("driver"),maps.get("url"),maps.get("username"),maps.get("password"));
        }
        if (CONST.POOLED_DATASOURCE.equals(type)){
            dataSource = new PooledDataSource(poolName,maps.get("driver"),maps.get("url"),maps.get("username"),maps.get("password"));
        }
        return dataSource;
    }

}
