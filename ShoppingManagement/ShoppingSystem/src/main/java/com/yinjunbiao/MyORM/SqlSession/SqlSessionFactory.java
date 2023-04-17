package com.yinjunbiao.MyORM.SqlSession;

import com.yinjunbiao.MyORM.MappedStatement.MappedStatement;
import com.yinjunbiao.MyORM.MappedStatement.ResultMap;
import com.yinjunbiao.MyORM.Transaction.Transaction;

import java.util.Map;

/**
 * @author yinjunbiao
 * @version 1.0
 */
public class SqlSessionFactory {

    private Transaction transaction;


    private Map<String , MappedStatement> mappedStatementMaps;

    private Map<String , ResultMap> resultMaps;

    public SqlSessionFactory() {
    }


    public SqlSessionFactory(Transaction transaction, Map<String, MappedStatement> mappedStatementMaps, Map<String, ResultMap> resultMaps) {
        this.transaction = transaction;
        this.mappedStatementMaps = mappedStatementMaps;
        this.resultMaps = resultMaps;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    public Map<String, ResultMap> getResultMaps() {
        return resultMaps;
    }

    public void setResultMaps(Map<String, ResultMap> resultMaps) {
        this.resultMaps = resultMaps;
    }

    public Map<String, MappedStatement> getMappedStatementMaps() {
        return mappedStatementMaps;
    }

    public void setMappedStatementMaps(Map<String, MappedStatement> mappedStatementMaps) {
        this.mappedStatementMaps = mappedStatementMaps;
    }

    public SqlSession openSqlSession(){
        transaction.openConnection();
        return new SqlSession(this);
    }
}
