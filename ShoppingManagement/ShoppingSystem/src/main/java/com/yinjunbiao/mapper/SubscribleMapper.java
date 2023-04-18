package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Delete;
import com.yinjunbiao.MyORM.Annotation.Insert;
import com.yinjunbiao.MyORM.Annotation.Param;

public interface SubscribleMapper {
    @Insert(sql = "insert into subscrible values (null,#{userId},#{shopId})")
    int insert(@Param("userId")Integer userId,@Param("shopId")Integer shopId);

    @Delete(sql = "delete from subscription where user_id = #{userId} and shop_id = #{shopId}")
    int deleteByUASId(@Param("userId")Integer userId,@Param("shopId")Integer shopId);
}
