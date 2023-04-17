package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MyORM.Annotation.Update;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.User;

@Mapper
@Component("userMapper")
public interface UserMapper {
    @Select(sql = "select * from user where phone = #{phone}")
    User select(@Param("phone")String phoneshay);

    @Update(sql = "update user set cnt = #{cnt} where phone = #{phone}")
    int updateCnt(@Param("cnt")int cnt,@Param("phone")String userName);
}
