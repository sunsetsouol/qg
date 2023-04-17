package com.yinjunbiao.MyORM.SqlSession.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface TypeHandler<T> {
    void setParam(PreparedStatement preparedStatement, int i, T value) throws SQLException;
}
