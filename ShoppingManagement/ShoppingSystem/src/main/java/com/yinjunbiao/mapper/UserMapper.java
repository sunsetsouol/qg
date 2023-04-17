package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.entity.User;

public interface UserMapper {
    @Select(sql = "select * from user")
    User select();
}
