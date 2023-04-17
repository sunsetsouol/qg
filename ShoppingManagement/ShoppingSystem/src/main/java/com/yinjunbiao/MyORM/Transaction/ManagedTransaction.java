package com.yinjunbiao.MyORM.Transaction;

import java.sql.Connection;

public class ManagedTransaction implements Transaction {
    @Override
    public void commit() {

    }

    @Override
    public void close() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void openConnection() {

    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
