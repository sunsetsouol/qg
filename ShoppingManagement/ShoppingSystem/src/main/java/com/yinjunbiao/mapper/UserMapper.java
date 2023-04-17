package com.yinjunbiao.mapper;

import com.mysql.cj.jdbc.Blob;
import com.yinjunbiao.MyORM.Annotation.Insert;
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
    User select(@Param("phone")String phone);

    @Update(sql = "update user set cnt = #{cnt} where phone = #{phone}")
    int updateCnt(@Param("cnt")int cnt,@Param("phone")String userName);

    @Insert(sql = "insert into user values(null,0,#{phone},null,#{userName},#{address},#{password},#{isPrivate},5)")
    int add(@Param("phone")String phone, @Param("userName")String userName,@Param("address")String address,@Param("password")String password,@Param("isPrivate")Integer isPrivate);



}
