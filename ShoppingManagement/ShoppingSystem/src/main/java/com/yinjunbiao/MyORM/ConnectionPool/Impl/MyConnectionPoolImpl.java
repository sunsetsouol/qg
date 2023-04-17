package com.yinjunbiao.MyORM.ConnectionPool.Impl;

import com.yinjunbiao.MyORM.ConnectionPool.MyConnectionPool;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 手写连接池
 *
 * @author yinjunbiao
 * @version 1.0
 */
public class MyConnectionPoolImpl implements MyConnectionPool {
    /**
     * 阻塞队列
     * 活跃连接
     * 空闲连接
     * 当前线程数
     */
    static LinkedBlockingQueue<Object> queue = null;

    final LinkedList<Connection> pool = new LinkedList<>();


    private String driverName;

    private String url;
    private String userName;
    private String password;

    private String poolName;


    private int minConnections;

    private int maxConnections;

    private int coreConnections;

    private long connTimeOut;

    private AtomicInteger count = null;


    /**
     * 构造函数 根据路径文件读取信息
     *
     * @param path 资源路径
     */
    public MyConnectionPoolImpl(String path, String driverName, String url, String userName, String password) {
        try {
            this.driverName = driverName;
            this.url = url;
            this.userName = userName;
            this.password = password;
            Class.forName(driverName);
            Properties properties = new Properties();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
            properties.load(inputStream);
            poolName = properties.getProperty("poolName");
            maxConnections = Integer.parseInt(properties.getProperty("maxConnections"));
            minConnections = Integer.parseInt(properties.getProperty("minConnections"));
            coreConnections = Integer.parseInt(properties.getProperty("coreConnections"));
            connTimeOut = Integer.parseInt(properties.getProperty("connTimeOut"));
            queue = new LinkedBlockingQueue<>(coreConnections);
            count = new AtomicInteger(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addConnection(coreConnections);
    }

    /**
     * 创建连接,添加到连接池中
     */
    private void addConnection(int num) {

        for (int i = 0; i < num; i++) {
            Connection connection = newConnection();
            if (connection != null) {
                pool.add(connection);
            }
        }

    }

    /**
     * 数据库连接池扩容
     */
    private void expand() {
        addConnection(maxConnections - coreConnections);
        queue = new LinkedBlockingQueue<>(maxConnections);
        synchronized (queue) {
            for (int i = 0; i < count.get(); i++) {
                queue.offer(new Object());
            }
        }
    }

    /**
     * 缩容
     */
    private void shrink() {
        synchronized (pool) {
            try {
                for (int i = maxConnections - coreConnections; i > 0; i--) {
                    Connection connection = pool.removeLast();
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            queue = new LinkedBlockingQueue<>(coreConnections);
            for (int i = 0; i < count.get(); i++) {
                queue.offer(new Object());
            }
        }

    }

    /**
     * 创建新的连接
     *
     * @return 返回新创建的连接
     */
    private Connection newConnection() {

        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            count.incrementAndGet();
            return connection;
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    @Override
    public Connection getConnection() {
        Connection connection = null;
        //如果当前线程数没有达到上限，如果空闲线程池中有空闲连接，则直接获取，如果没有则新建连接，如果达到上限，则加入阻塞队列等待
        if (pool.size() == 0 && queue.size() == coreConnections) {
            synchronized (pool) {
                if (pool.size() == 0 && queue.size() == coreConnections) {
                    expand();
                }
            }
        }
        if (pool.size() > 0) {
            synchronized (pool) {
                if (pool.size() > 0) {
                    connection = pool.removeLast();
                    queue.offer(new Object());
                }
            }
        }
        if (connection == null) {
            try {
                //如果线程线程数量达到上限，则加入阻塞队列
                queue.offer(new Object(), connTimeOut, TimeUnit.MILLISECONDS);
                connection = getConnection();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private boolean isAvaliable(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * 返回连接
     *
     * @param connection 连接对象
     */
    @Override
    public synchronized void releaseConnection(Connection connection) {
        //如果conncection不为空且未关闭，如果空闲连接队列没满则直接放进空闲队列，如果满了则关闭连接
        try {
            if (connection != null) {
                if (isAvaliable(connection)) {
                    pool.addFirst(connection);
                } else {
                    connection.close();
                    count.decrementAndGet();
                }
                queue.poll();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(count.get() < coreConnections && pool.size() > maxConnections-coreConnections+minConnections){
            synchronized (pool){
                if (count.get() < coreConnections && pool.size() > maxConnections-coreConnections+minConnections){
                    shrink();
                }
            }
        }
    }
}
