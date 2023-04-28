package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.PushGood;

import java.util.List;

@Mapper
@Component("pushGoodsMapper")
public interface PushGoodsMapper {

    @Insert(sql = "insert into push values(null,#{name},#{price},0,null,#{description},#{shopId},#{inventory})")
    @ResultMap(id = "pushGoodsResultMap")
    int insert(@Param("name")String name,@Param("price")Integer price,@Param("description")String description,@Param("shopId")Integer shopId,@Param("inventory")Integer inventory);


    @Select(sql = "select * from push where shop_id = #{shopId} and status = 0")
    @ResultMap(id = "pushGoodsResultMap")
    List<PushGood> selectByShopId(@Param("shopId")Integer shopId);

    @Select(sql = "select * from push where status = 0")
    @ResultMap(id = "pushGoodsResultMap")
    List<PushGood> select();

    @Select(sql = "select * from push where shop_id = #{shopId} and name = #{name} and status = 0")
    @ResultMap(id = "pushGoodsResultMap")
    PushGood selectByShopIdAndName(@Param("shopId")Integer shopId,@Param("name")String name);

    @Select(sql = "select * from push where id = #{id} and status = 0")
    @ResultMap(id = "pushGoodsResultMap")
    PushGood selectById(@Param("id")Long id);

    @Select(sql = "select * from push where name like #{name}")
    @ResultMap(id = "pushGoodsResultMap")
    List<PushGood> selectByName(@Param("name")String name);

    @Select(sql = "select * from push where status = #{status}")
    @ResultMap(id = "pushGoodsResultMap")
    List<PushGood> selectByStatus(@Param("status")Integer status);

    @Update(sql = "update push set name = #{name} where id = #{id}")
    @ResultMap(id = "pushGoodsResultMap")
    int updateName(@Param("name")String name,@Param("id")Long id);

    @Update(sql = "update push set price = #{price} where id = #{id}")
    @ResultMap(id = "pushGoodsResultMap")
    int updatePrice(@Param("price")Integer price,@Param("id")Long id);

    @Update(sql = "update push set status = #{status} where id = #{id}")
    @ResultMap(id = "pushGoodsResultMap")
    int updateStatus(@Param("status")Integer status,@Param("id")Long id);

    @Update(sql = "update push set picture = #{picture} where id = #{id}")
    @ResultMap(id = "pushGoodsResultMap")
    int updatePicture(@Param("picture")String picture,@Param("id")Long id);
}
