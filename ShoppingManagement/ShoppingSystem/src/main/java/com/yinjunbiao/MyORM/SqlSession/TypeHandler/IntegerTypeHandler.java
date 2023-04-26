package com.yinjunbiao.MyORM.SqlSession.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class IntegerTypeHandler implements TypeHandler<Integer> {
    @Override
    public void setParam(PreparedStatement preparedStatement, int i, Integer value) throws SQLException {
        if (value != null){
            preparedStatement.setInt(i,value);
        }else {
            preparedStatement.setNull(i, Types.INTEGER);
        }
    }
}
