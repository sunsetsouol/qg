package com.yinjunbiao.MyORM.Transaction;

import com.yinjunbiao.MyORM.ConnectionPool.MyConnectionPool;
import com.yinjunbiao.MyORM.DataSource.PooledDataSource;
import com.yinjunbiao.MyORM.DataSource.UnPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC事务管理器
 * @author yinjunbiao
 * @version 1.0
 */
public class JdbcTransaction implements Transaction {

    private DataSource dataSource ;

    private boolean autoCommit;

    private static ThreadLocal<Connection> THREAD = new ThreadLocal<>();

    public JdbcTransaction() {
    }

    public JdbcTransaction(DataSource dataSource, boolean autoCommit) {
        this.dataSource = dataSource;
        this.autoCommit = autoCommit;
    }

    @Override
    public void commit() {
        try {
            if (THREAD.get() == null){
                Connection connection = dataSource.getConnection();
                connection.setAutoCommit(autoCommit);
                THREAD.set(connection);
            }
            THREAD.get().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (THREAD.get() == null){
                Connection connection = dataSource.getConnection();
                connection.setAutoCommit(autoCommit);
                THREAD.set(connection);
            }
            //数据库连接池
            if(dataSource instanceof PooledDataSource){
                commit();
                PooledDataSource pooledDataSource = (PooledDataSource) dataSource;
                MyConnectionPool pool = pooledDataSource.getPool();
                if (THREAD.get() != null){
                    pool.releaseConnection(THREAD.get());
                    THREAD.remove();
                }
            }
            //非连接池
            if (dataSource instanceof UnPooledDataSource){
                if (THREAD.get() != null){
                    THREAD.get().close();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            Connection connection = THREAD.get();
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void openConnection() {
        try {
            Connection connection = THREAD.get();
            if(connection == null){
                connection = dataSource.getConnection();
                connection.setAutoCommit(autoCommit);
                THREAD.set(connection);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        Connection connection;
        if (THREAD.get() == null){
            try {
                connection = dataSource.getConnection();
                connection.setAutoCommit(autoCommit);
                THREAD.set(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return THREAD.get();
    }
}
