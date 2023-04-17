package com.yinjunbiao.MyORM.SqlSession;

import com.yinjunbiao.MyORM.MappedStatement.MappedStatement;
import com.yinjunbiao.MyORM.SqlSession.TypeHandler.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlSession {

    public static Map<Class, TypeHandler> getTypeHandlerMap() {
        return typeHandlerMap;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    private SqlSessionFactory sqlSessionFactory;

    private static final Map<Class, TypeHandler> typeHandlerMap = new HashMap<>();

    private Connection getConnection(){
        return sqlSessionFactory.getTransaction().getConnection();
    }

    static {
        typeHandlerMap.put(String.class,new StringTypeHandler());
        typeHandlerMap.put(Integer.class,new IntegerTypeHandler());
        typeHandlerMap.put(int.class,new IntegerTypeHandler());
        typeHandlerMap.put(double.class,new DoubleTypeHandler());
        typeHandlerMap.put(Double.class,new DoubleTypeHandler());
        typeHandlerMap.put(long.class,new LongTypeHandler());
        typeHandlerMap.put(Long.class,new LongTypeHandler());
    }


    public SqlSession() {
    }

    public SqlSession(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 查找一个对象
     * @param sqlId sql语句对应id
     * @param params 参数集
     * @return 返回结果对象
     */
    public Object selectOne(String sqlId, Map<String,Object> params){
        //初始化
        Object obj = null;
        //获取连接
        Connection connection = sqlSessionFactory.getTransaction().getConnection();

        try {
            //sql语句，替换占位符生成preparedStatement
            MappedStatement mappedStatement = sqlSessionFactory.getMappedStatementMaps().get(sqlId);
            String sql = mappedStatement.getSql();
            PreparedStatement preparedStatement = getPrepareStatement(connection,sql,params);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Class<?> returnClass = Class.forName(mappedStatement.getResultType());
                obj = returnClass.getDeclaredConstructor().newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    columnName = columnName.toUpperCase().charAt(0) + columnName.substring(1);
                    String methodName = "set" + columnName;
                    Method setMethod = returnClass.getDeclaredMethod(methodName, returnClass.getDeclaredMethod("get" + columnName).getReturnType());

                    setMethod.invoke(obj,resultSet.getObject(i+1));

                }
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return obj;
    }

    public List<Object> selectList(String sqlId, Map<String ,Object> params){
        List<Object> list = new ArrayList<>();
        Connection connection = sqlSessionFactory.getTransaction().getConnection();
        try {
            String sql =sqlSessionFactory.getMappedStatementMaps().get(sqlId).getSql();

            PreparedStatement preparedStatement = getPrepareStatement(connection,sql,params);

            ResultSet resultSet = preparedStatement.executeQuery();
            Class<?> pojoClass = Class.forName(sqlSessionFactory.getMappedStatementMaps().get(sqlId).getResultType());
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<Method> setMethods = new ArrayList<>();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i + 1);
                columnName = columnName.toUpperCase().charAt(0)+columnName.substring(1);
                Method setMethod = pojoClass.getDeclaredMethod("set" + columnName, pojoClass.getDeclaredMethod("get" + columnName).getReturnType());
                setMethods.add(setMethod);
            }
            while (resultSet.next()){
                Object obj = pojoClass.getDeclaredConstructor().newInstance();
                for (int i = 0; i < setMethods.size(); i++) {
                    setMethods.get(i).invoke(obj,resultSet.getObject(i+1));
                }
                list.add(obj);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public int update(String sqlId, Map<String ,Object> params){
        int cnt = 0;
        try {

            Connection connection = sqlSessionFactory.getTransaction().getConnection();
            String sql = sqlSessionFactory.getMappedStatementMaps().get(sqlId).getSql();
            PreparedStatement preparedStatement = getPrepareStatement(connection,sql,params);

            cnt = preparedStatement.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return cnt;
    }



    public void commit(){
        sqlSessionFactory.getTransaction().commit();
    }

    public void rollback(){
        sqlSessionFactory.getTransaction().rollback();
    }

    public void close(){
        sqlSessionFactory.getTransaction().close();
    }

    private static PreparedStatement getPrepareStatement(Connection connection, String sql, Map<String ,Object>params){
        PreparedStatement preparedStatement = null;

        try {
            String batisSql = sql.replaceAll("#\\{[0-9a-zA-Z]*}","?");
            preparedStatement = connection.prepareStatement(batisSql);
            int index = 1;
            int start = 0;
            int end = -1;
            while (start >= 0 && end<sql.length()){
                start = sql.indexOf("#",end+1);
                end = sql.indexOf("}",end+1);
                if (start < 0){
                    break;
                }
                String key = sql.substring(start+2,end).trim();
                Object param = params.get(key);
                typeHandlerMap.get(param.getClass()).setParam(preparedStatement,index,param);
                index++;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return preparedStatement;


    }

    /**
     * 最新版方式，动态代理进行操作
     * @param mapper mapper接口的类
     * @param <T> mapper泛型
     * @return 代理对象
     */
    public <T>T getMapper(Class<T> mapper){
        Object instance = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapper}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        return (T) instance;
    }

}
