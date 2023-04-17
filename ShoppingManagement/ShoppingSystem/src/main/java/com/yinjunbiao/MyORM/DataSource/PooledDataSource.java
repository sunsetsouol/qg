package com.yinjunbiao.MyORM.DataSource;

import com.yinjunbiao.MyORM.ConnectionPool.Impl.MyConnectionPoolImpl;
import com.yinjunbiao.MyORM.ConnectionPool.MyConnectionPool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * 使用数据库连接池
 * @author yinjunbiao
 * @version 1.0
 */
public class PooledDataSource implements DataSource {
    private MyConnectionPool pool ;

    public PooledDataSource(String path,String driver,String url,String username,String password) {
        this.pool = new MyConnectionPoolImpl(path,driver,url,username,password);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    public MyConnectionPool getPool() {
        return pool;
    }

    public void setPool(MyConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
