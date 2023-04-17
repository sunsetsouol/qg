package com.yinjunbiao.MyORM.SqlSession.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IntegerTypeHandler implements TypeHandler<Integer> {
    @Override
    public void setParam(PreparedStatement preparedStatement, int i, Integer value) throws SQLException {
        preparedStatement.setInt(i,value);
    }
}
