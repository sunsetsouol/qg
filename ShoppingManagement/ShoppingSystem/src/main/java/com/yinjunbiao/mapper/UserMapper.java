package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.User;

import java.util.List;

@Mapper
@Component("userMapper")
public interface UserMapper {

    @Select(sql = "select * from user where id = #{id}")
    @ResultMap(id = "userResultMap")
    User selectById(@Param("id")Integer id);


    @Select(sql = "select * from user where phone = #{phone}")
    @ResultMap(id = "userResultMap")
    User selectByPhone(@Param("phone")String phone);

    @Select(sql = "select user_name,headshot from user where user_name like #{userName} and isprivate = 0")
    @ResultMap(id = "userResultMap")
    List<User> selectByName(@Param("userName")String userName);

    @Insert(sql = "insert into user values(null,0,#{phone},null,#{userName},#{address},#{password},#{isPrivate},5)")
    @ResultMap(id = "userResultMap")
    int insert(@Param("phone")String phone, @Param("userName")String userName,@Param("address")String address,@Param("password")String password,@Param("isPrivate")Integer isPrivate);


    @Update(sql = "update user set cnt = #{cnt} where phone = #{phone}")
    @ResultMap(id = "userResultMap")
    int updateCnt(@Param("cnt")int cnt,@Param("phone")String userName);

    @Update(sql = "update user set password = #{password} where id = #{id}")
    @ResultMap(id = "userResultMap")
    int updatePassword(@Param("password") String password, @Param("id") Integer id);

    @Update(sql = "update user set phone = #{phone} where id = #{id}")
    @ResultMap(id = "userResultMap")
    int updatePhone(@Param("phone")String phone,@Param("id") Integer id);

    @Update(sql = "update user set identify = #{identify} where id = #{id}")
    @ResultMap(id = "userResultMap")
    int updateIdentify(@Param("identify")Integer identify,@Param("id") Integer id);

    @Update(sql = "update user set user_name = #{userName} where id = #{id}")
    @ResultMap(id = "userResultMap")
    int updateUserName(@Param("userName")String userName,@Param("id") Integer id);

    @Update(sql = "update user set isprivate = #{isPrivate} where id = #{id}")
    @ResultMap(id = "userResultMap")
    int updateIsPrivate(@Param("isPrivate")Integer isPrivatem,@Param("id") Integer id);

    @Update(sql = "update user set address = #{address} where id = #{id}")
    @ResultMap(id = "userResultMap")
    int updateAddress(@Param("address")String address,@Param("id") Integer id);

    @Update(sql = "update user set headshot = #{headshot} where id = #{id}")
    @ResultMap(id = "userResultMap")
    int updateHeadshot(@Param("headshot")String headshot,@Param("id") Integer id);

    @Update(sql = "update user set user_name = #{userName},address = #{address},isprivate = #{isPrivate} where id = #{id}")
    @ResultMap(id = "userResultMap")
    int updateMessage(@Param("userName")String userName,@Param("address")String address,@Param("isPrivate")Integer isPrivate);

}
