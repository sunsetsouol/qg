package com.yinjunbiao.MyORM.SqlSession.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LongTypeHandler implements TypeHandler<Long> {
    @Override
    public void setParam(PreparedStatement preparedStatement, int i, Long value) throws SQLException {
        preparedStatement.setLong(i,value);
    }
}
