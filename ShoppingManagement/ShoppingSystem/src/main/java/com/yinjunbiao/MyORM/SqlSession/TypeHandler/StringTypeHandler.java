package com.yinjunbiao.MyORM.SqlSession.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class StringTypeHandler implements TypeHandler<String> {
    @Override
    public void setParam(PreparedStatement preparedStatement, int i, String value) throws SQLException {
        if (value != null){
            preparedStatement.setString(i,value);
        }else {
            preparedStatement.setNull(i, Types.VARCHAR);
        }
    }
}
