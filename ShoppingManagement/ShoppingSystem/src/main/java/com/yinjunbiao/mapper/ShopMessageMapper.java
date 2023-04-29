package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.ShopMessage;

import java.util.List;

@Mapper
@Component("shopMessageMapper")
public interface ShopMessageMapper {

    @Select(sql = "select * from shop_message where shop_id = #{shopId} order by id desc")
    @ResultMap(id = "shopMessageResultMap")
    List<ShopMessage> selectByShopId(@Param("shopId")Integer shopId);

    @Insert(sql = "insert into shop_message values (null,#{shopId},#{message})")
    @ResultMap(id = "shopMessageResultMap")
    int insert(@Param("shopId") Integer shopId, @Param("message")String message);

    @Delete(sql = "delete from shop_message where id = #{id}")
    @ResultMap(id = "shopMessageResultMap")
    int deleteById(@Param("id")Integer id);
}
