package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Apply;

import java.util.List;

@Mapper
@Component("applyMapper")
public interface ApplyMapper {

    @Select(sql = "select * from apply where status = 0")
    @ResultMap(id = "applyResultMap")
    List<Apply> select();

    @Select(sql = "select * from apply where user_id = #{userId} and status = 0")
    @ResultMap(id = "applyResultMap")
    Apply selectByUserId(@Param("userId")Integer id);

    @Insert(sql = "insert into apply values (null,#{userId},0,#{shopName},#{description})")
    @ResultMap(id = "applyResultMap")
    int insert (@Param("userId")Integer userId,@Param("shopName")String shopName,@Param("description")String description);

    @Update(sql = "update apply set status = #{status} where id = #{id}")
    @ResultMap(id = "applyResultMap")
    int updateStatus(@Param("status")Integer status,@Param("id")Integer id);


}
