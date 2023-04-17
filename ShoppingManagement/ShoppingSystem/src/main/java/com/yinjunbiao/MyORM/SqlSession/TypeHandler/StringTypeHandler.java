package com.yinjunbiao.MyORM.SqlSession.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StringTypeHandler implements TypeHandler<String> {
    @Override
    public void setParam(PreparedStatement preparedStatement, int i, String value) throws SQLException {
        preparedStatement.setString(i,value);
    }
}
