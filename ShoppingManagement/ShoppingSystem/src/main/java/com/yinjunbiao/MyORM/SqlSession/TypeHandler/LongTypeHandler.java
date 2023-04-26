package com.yinjunbiao.MyORM.SqlSession.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class LongTypeHandler implements TypeHandler<Long> {
    @Override
    public void setParam(PreparedStatement preparedStatement, int i, Long value) throws SQLException {
        if (value != null){
            preparedStatement.setLong(i,value);
        }else {
            preparedStatement.setNull(i, Types.BIGINT);
        }
    }
}
