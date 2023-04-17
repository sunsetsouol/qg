package com.yinjunbiao.MyORM.ConnectionPool;

import java.sql.Connection;

public interface MyConnectionPool {

    /**
     * 获取连接
     * @return 数据库连接
     */
    Connection getConnection();

    /**
     * 返回连接
     * @param connection
     */
    void releaseConnection(Connection connection);
}
