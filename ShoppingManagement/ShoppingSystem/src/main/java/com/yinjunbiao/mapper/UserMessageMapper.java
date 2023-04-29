package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.UserMessage;

import java.util.List;

@Mapper
@Component("userMessageMapper")
public interface UserMessageMapper {

    @Select(sql = "select * from user_message where user_id = #{userId} order by id desc")
    @ResultMap(id = "userMessageResultMap")
    List<UserMessage> selectByUserId(@Param("userId")Integer userId);

    @Insert(sql = "insert into user_message values (null,#{userId},#{message})")
    @ResultMap(id = "userMessageResultMap")
    int insert(@Param("userId")Integer userId,@Param("message")String message);

    @Delete(sql = "delete from user_message where id = #{id}")
    @ResultMap(id = "userMessageResultMap")
    int deleteById(@Param("id")Integer id);

}
