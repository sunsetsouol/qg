package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Goods;

import java.util.List;

@Mapper
@Component("goodsMapper")
public interface GoodsMapper {

    @Select(sql = "select * from goods limit #{begin},#{size}")
    @ResultMap(id = "goodsResultMap")
    List<Goods> selectAll(@Param("begin")Integer begin,@Param("size")Integer size);

    @Select(sql = "select * from goods where description like #{name} or name like #{name} or shop_name like #{name} limit #{begin},#{size}")
    @ResultMap(id = "goodsResultMap")
    List<Goods> selectByName(@Param("name")String name,@Param("begin")Integer begin,@Param("size")Integer size);

    @Select(sql = "select * from goods where shop_id = #{shopId} limit #{begin},#{size}")
    @ResultMap(id = "goodsResultMap")
    List<Goods> selectByShopId(@Param("shopId")Integer shopId,@Param("begin")Integer begin,@Param("size")Integer size);

    @Select(sql = "select * from goods where id = #{id}")
    @ResultMap(id = "goodsResultMap")
    Goods selectById(@Param("id")Long id);

    @Insert(sql = "insert into goods values(null,#{shopId},#{shopName},#{description},0,#{inventory},#{price},#{picture},#{name})")
    @ResultMap(id = "goodsResultMap")
    int insert(@Param("shopId")Integer shopId,@Param("shopName")String shopName,@Param("description")String description,@Param("inventory")Integer inventory,@Param("price")Integer price,@Param("picture")String picture,@Param("name")String name);

    @Delete(sql = "delete from goods where id = #{id}")
    @ResultMap(id = "goodsResultMap")
    int deleteById(@Param("id")Long id);

    @Update(sql = "update goods set description = #{description} where id = #{id}")
    @ResultMap(id = "goodsResultMap")
    int updateDesc(@Param("description")String description,@Param("id")Long id);

    @Update(sql = "update goods set price = #{price} where id = {id}")
    @ResultMap(id = "goodsResultMap")
    int updatePrice(@Param("price")Integer price,@Param("id")Long id);

    @Update(sql = "update goods set picture = #{picture} where id = #{id}")
    @ResultMap(id = "goodsResultMap")
    int updatePicture(@Param("picture")String picture,@Param("id")Long id);

    @Update(sql = "update goods set sales = #{sales} where id = #{id}")
    @ResultMap(id = "goodsResultMap")
    int updateSales(@Param("sales")Integer sales,@Param("id")Long id);

    @Update(sql = "update goods set inventory = #{inventory} where id = #{id}")
    @ResultMap(id = "goodsResultMap")
    int updateInventory(@Param("inventory")Integer inventory,@Param("id")Long id);

    @Update(sql = "update goods set name = #{name} where id = #{id}")
    @ResultMap(id = "goodsResultMap")
    int updateName(@Param("name")String name,@Param("id")Long id);

}
