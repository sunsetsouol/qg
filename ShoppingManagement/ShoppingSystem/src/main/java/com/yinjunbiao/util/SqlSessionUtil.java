package com.yinjunbiao.util;


import com.yinjunbiao.MyORM.SqlSession.SqlSession;
import com.yinjunbiao.MyORM.SqlSession.SqlSessionFactory;
import com.yinjunbiao.MyORM.SqlSession.SqlSessionFactoryBuilder;

/**
 * SqlSession工具类
 * @author yinjunbiao
 */
public class SqlSessionUtil {

    private static SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(SqlSessionUtil.class.getClassLoader().getResourceAsStream(CONST.CONFIGXML));

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSqlSession();
    }

    public static void commit(){
        sqlSessionFactory.getTransaction().commit();
    }

    public static void close(){
        sqlSessionFactory.getTransaction().close();
    }

    public static void rollback(){
        sqlSessionFactory.getTransaction().rollback();
    }
}
