package com.yinjunbiao.MyORM.MappedStatement;

/**
 * pojo类，封装SQL标签
 * SQL标签封装到MappedStatement中
 * @author yinjunbiao
 * @version 1.0
 */
public class MappedStatement {
    private String sql;
    private String resultType;
    private String resultMapId;

    @Override
    public String toString() {
        return "MappedStatement{" +
                "sql='" + sql + '\'' +
                ", resultType='" + resultType + '\'' +
                ", resultMapId='" + resultMapId + '\'' +
                '}';
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultMapId() {
        return resultMapId;
    }

    public void setResultMapId(String resultMapId) {
        this.resultMapId = resultMapId;
    }

    public MappedStatement() {
    }

    public MappedStatement(String sql, String resultType, String resultMapId) {
        this.sql = sql;
        this.resultType = resultType;
        this.resultMapId = resultMapId;
    }
}
