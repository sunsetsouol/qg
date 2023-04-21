package com.yinjunbiao.MyORM.SqlSession;

import com.yinjunbiao.Exception.MapperException;
import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MyORM.MappedStatement.MappedStatement;
import com.yinjunbiao.MyORM.MappedStatement.ResultMap;
import com.yinjunbiao.util.CONST;
import com.yinjunbiao.util.ParseSql;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反射函数
 * @author yinjunbiao
 */
public class InvocationHandlerImpl implements InvocationHandler {
    private Class mapper;
    private SqlSession sqlSession;

    public InvocationHandlerImpl() {
    }

    public InvocationHandlerImpl(Class mapper, SqlSession sqlSession) {
        this.mapper = mapper;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String sql = null;

        //获取注解，解析sql语句
        Select select = method.getAnnotation(Select.class);
        Insert insert = method.getAnnotation(Insert.class);
        Delete delete = method.getAnnotation(Delete.class);
        Update update = method.getAnnotation(Update.class);
        if(select != null){
            sql = select.sql();
        }else if (insert != null){
            sql = insert.sql();
        }else if (delete != null){
            sql = delete.sql();
        }else if(update != null){
            sql = update.sql();
        }
        //如果没有sql语句则在xml文件中找对听sql语句
        if(sql == null){
            String sqlId = mapper.getName() + "." + method.getName();

            MappedStatement mappedStatement = sqlSession.getSqlSessionFactory().getMappedStatementMaps().get(sqlId);
            if(mappedStatement == null){
                throw new MapperException("找不到匹配的sql语句");
            }
            sql = mappedStatement.getSql();
        }

        //设置sql语句的参数
        Map<String ,Object> paramMaps = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        //参数列表
        for (int i = 0; i < parameters.length; i++) {
            Param param = parameters[i].getAnnotation(Param.class);
            if(param != null){
                paramMaps.put(param.value(),args[i]);
            }
        }

        //替换sql语句变量
        ParseSql parseSql = new ParseSql();
        sql = parseSql.parse(sql);
        List<String> params = parseSql.getParams();
        Connection connection = sqlSession.getSqlSessionFactory().getTransaction().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //从map中找到对应的value
        for (int i = 0; i < params.size(); i++) {
            Object value = paramMaps.get(params.get(i));
            if(value == null){
                throw new MapperException("没有找到需要的参数"+paramMaps.get(i));
            }
            Class<?> type = value.getClass();
            SqlSession.getTypeHandlerMap().get(type).setParam(preparedStatement,i+1,value);
        }
        if(!sql.startsWith(CONST.SELECT)){
            return preparedStatement.executeUpdate();
        }
        ResultSet resultSet = preparedStatement.executeQuery();



        //获取参数名
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<String >columns = new ArrayList<>();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i + 1);
            columns.add(columnName);
        }

        //获取返回对象信息
        Class resultClass = null;
        Type genericReturnType = method.getGenericReturnType();
        if(genericReturnType instanceof Class){
            //不是泛型
            resultClass = (Class) genericReturnType;
        }else if(genericReturnType instanceof ParameterizedType){
            //泛型
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            resultClass = (Class) actualTypeArguments[0];
        }

        com.yinjunbiao.MyORM.Annotation.ResultMap resultMap = method.getAnnotation(com.yinjunbiao.MyORM.Annotation.ResultMap.class);
        Map<String, String> resultMaps = null;
        if (resultMap != null){
            String id = resultMap.id();
            if(id != null){
                ResultMap map = sqlSession.getSqlSessionFactory().getResultMaps().get(id);
                if (map != null){
                    resultMaps = map.getResultMaps();
                }
            }
        }

        //根据返回对象查找set方法
        Map<String , Method>setMethodMap = new HashMap<>();
        Method[] declaredMethods = resultClass.getDeclaredMethods();

        //循环查找set方法
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().startsWith(CONST.SET)){
                String property = declaredMethod.getName().substring(3);
                property = property.toLowerCase().charAt(0) + property.substring(1);
                if(resultMaps != null && resultMaps.get(property) != null){
                    property = resultMaps.get(property);
                }
                setMethodMap.put(property,declaredMethod);
            }
        }


        //返回集
        List<Object>result = new ArrayList<>();

        if(columns.size() == 1 || resultClass.getConstructors().length == 0){
            while (resultSet.next()){
                result.add(resultSet.getObject(1));
            }
        }else {
            while (resultSet.next()){
                Object instance = resultClass.getConstructor().newInstance();
                for (String column : columns) {
                    Method setMethod = setMethodMap.get(column);
                    if(setMethod == null){
                        continue;
                    }
                    setMethod.invoke(instance,resultSet.getObject(column));
                }
                result.add(instance);
            }
        }
        resultSet.close();
        //判断返回类型
        if(method.getReturnType().equals(List.class)){
            return result;
        }
        if (result.size()!=0){
            return result.get(0);
        }
        return null;
    }
}
