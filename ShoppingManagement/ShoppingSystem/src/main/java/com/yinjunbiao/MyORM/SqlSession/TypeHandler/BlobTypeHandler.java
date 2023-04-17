package com.yinjunbiao.MyORM.SqlSession.TypeHandler;

import com.mysql.cj.jdbc.Blob;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlobTypeHandler implements TypeHandler<Blob>{
    @Override
    public void setParam(PreparedStatement preparedStatement, int i, Blob value) throws SQLException {
        preparedStatement.setBlob(i,value);
    }
}
