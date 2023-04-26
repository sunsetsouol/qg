package com.yinjunbiao.MyORM.SqlSession.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class DoubleTypeHandler implements TypeHandler<Double> {
    @Override
    public void setParam(PreparedStatement preparedStatement, int i, Double value) throws SQLException {
        if (value != null){
            preparedStatement.setDouble(i,value);
        }else {
            preparedStatement.setNull(i, Types.DOUBLE);
        }
    }
}
