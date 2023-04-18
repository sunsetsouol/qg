package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Insert;
import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MyORM.Annotation.Update;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.User;

import java.util.List;

@Mapper
@Component("userMapper")
public interface UserMapper {
    @Select(sql = "select * from user where phone = #{phone}")
    User selectByPhone(@Param("phone")String phone);

    @Select(sql = "select * from user where user_name like #{userName}")
    List<User> selectByName(@Param("userName")String userName);

    @Insert(sql = "insert into user values(null,0,#{phone},null,#{userName},#{address},#{password},#{isPrivate},5)")
    int add(@Param("phone")String phone, @Param("userName")String userName,@Param("address")String address,@Param("password")String password,@Param("isPrivate")Integer isPrivate);


    @Update(sql = "update user set cnt = #{cnt} where phone = #{phone}")
    int updateCnt(@Param("cnt")int cnt,@Param("phone")String userName);

    @Update(sql = "update user set password = #{password} where phone = #{phone}")
    int updatePassword(@Param("password") String password, @Param("phone") String phone);

    @Update(sql = "update user set phone = #{phone} where phone = #{old}")
    int updatePhone(@Param("phone")String phone,@Param("old")String old);

    @Update(sql = "update user set identify = #{identify} where phone = {phone}")
    int updateIdentify(@Param("identify")Integer identify,@Param("phone")String phone);

    @Update(sql = "update user set user_name = #{userName} where phone = #{phone}")
    int updateUserName(@Param("userName")String userName,@Param("phone")String phone);

    @Update(sql = "update user set isprivate = #{isPrivate} where phone = #{phone}")
    int updateIsPrivate(@Param("isPrivate")Integer isPrivatem,@Param("phone")String phone);

    @Update(sql = "update user set address = #{address} where phone = #{phone}")
    int updateAddress(@Param("isPrivate")String address,@Param("phone")String phone);

    @Update(sql = "update user set headshot = #{headshot} where phone = #{phone}")
    int updateHeadshot(@Param("isPrivate")String address,@Param("phone")String phone);
}
