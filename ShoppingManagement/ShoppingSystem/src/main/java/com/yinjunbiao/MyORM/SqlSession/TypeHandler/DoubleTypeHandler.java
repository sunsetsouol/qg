package com.yinjunbiao.MyORM.SqlSession.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoubleTypeHandler implements TypeHandler<Double> {
    @Override
    public void setParam(PreparedStatement preparedStatement, int i, Double value) throws SQLException {
        preparedStatement.setDouble(i,value);
    }
}
