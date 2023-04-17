package com.yinjunbiao.MyORM.Transaction;

import java.sql.Connection;

/**
 * 事务管理器接口
 * 规范事务管理器
 * @author yinjunbiao
 * @version 1.0
 */
public interface Transaction {

    void commit();
    void close();
    void rollback();
    void openConnection();
    Connection getConnection();
}
