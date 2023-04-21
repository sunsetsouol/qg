package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Subscrible;

import java.util.List;

@Mapper
@Component("subscribleMapper")
public interface SubscribleMapper {
    @Insert(sql = "insert into subscrible values (null,#{userId},#{shopId})")
    @ResultMap(id = "subscribleResultMap")
    int insert(@Param("userId")Integer userId,@Param("shopId")Integer shopId);

    @Delete(sql = "delete from subscrible where user_id = #{userId} and shop_id = #{shopId}")
    @ResultMap(id = "subscribleResultMap")
    int deleteByUASId(@Param("userId")Integer userId,@Param("shopId")Integer shopId);

    @Select(sql = "select * from subscrible where user_id = #{userId}")
    @ResultMap(id = "subscribleResultMap")
    List<Subscrible> selectbyUserId(@Param("userId")Integer id);

    @Select(sql = "select * from subscrible where user_id = #{userId} and shop_id = #{shopId}")
    @ResultMap(id = "subscribleResultMap")
    Subscrible selectByUASId(@Param("userId")Integer userId,@Param("shopId")Integer shopId);
}
