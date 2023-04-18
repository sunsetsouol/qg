package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Goods;

import java.util.List;

@Mapper
@Component("goodsMapper")
public interface GoodsMapper {

    @Select(sql = "select * from goods")
    List<Goods> selectAll();

    @Select(sql = "select * from goods where description like #{name} or name like #{name}")
    List<Goods> selectByName(@Param("name")String name);

    @Select(sql = "select * from goods where shop_id = #{shopId}")
    List<Goods> selectByShopId(@Param("shopId")Integer shopId);

    @Select(sql = "select * from goods where id = #{id}")
    Goods selectById(@Param("id")Long id);

    @Insert(sql = "insert into goods values(null,#{shopId},#{description},0,#{inventory},#{price},#{picture},#{name}")
    int insert(@Param("shopId")Integer shopId,@Param("description")String description,@Param("inventory")Integer inventory,@Param("price")Integer price,@Param("picture")String picture,@Param("name")String name);

    @Delete(sql = "delete from goods where id = #{id}")
    int deleteById(@Param("id")Long id);

    @Update(sql = "update goods set description = #{description} where id = #{id}")
    int updateDesc(@Param("description")String description,@Param("id")Long id);

    @Update(sql = "update goods set price = #{price} where id = {id}")
    int updatePrice(@Param("price")Integer price,@Param("id")Long id);

    @Update(sql = "update goods set picture = #{picture} where id = #{id}")
    int updatePicture(@Param("picture")String picture,@Param("id")Long id);

    @Update(sql = "update goods set sales = #{sales} where id = #{id}")
    int updateSales(@Param("sales")Integer sales,@Param("id")Long id);

    @Update(sql = "update goods set inventory = #{inventory} where id = #{id}")
    int updateInventory(@Param("inventory")Integer inventory,@Param("id")Long id);

    @Update(sql = "update goods set name = #{name} where id = #{id}")
    int updateName(@Param("name")String name,@Param("id")Long id);

}
