package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Shop;

import java.util.List;

@Mapper
@Component("shopMapper")
public interface ShopMapper {

    @Select(sql = "select * from shop where name like #{name}")
    @ResultMap(id = "shopResultMap")
    List<Shop> selectIdByName(@Param("name")String name);

    @Select(sql = "select * from shop where description like #{description}")
    @ResultMap(id = "shopResultMap")
    List<Shop> selectIdByDesc(@Param("description")String description);

    @Select(sql = "select * from shop where id = #{id}")
    @ResultMap(id = "shopResultMap")
    Shop selectById(@Param("id")Integer id);

    @Select(sql = "select * from shop where boss_id = #{id}")
    @ResultMap(id = "shopResultMap")
    Shop selectByBossId(@Param("id")Integer id);

    @Insert(sql = "insert into shop values(null,#{bossId},0,#{description},0,#{shopName})")
    @ResultMap(id = "shopResultMap")
    int insert(@Param("bossId")Integer bossId,@Param("shopName")String shopName,@Param("description")String description);

    @Update(sql = "update shop set fans = #{fans} where id = #{id}")
    @ResultMap(id = "shopResultMap")
    int updateFans(@Param("fans")Integer fans,@Param("id")Integer id);

    @Update(sql = "update shop set description = #{description} where id = #{id}")
    @ResultMap(id = "shopResultMap")
    int updateDesc(@Param("description")String  description,@Param("id")Integer id);

    @Update(sql = "update shop set sales = #{sales} where id = #{id}")
    @ResultMap(id = "shopResultMap")
    int updateSales(@Param("sales")Integer sales,@Param("id")Integer id);

    @Update(sql = "update shop set name = #{name} where id = #{id}")
    @ResultMap(id = "shopResultMap")
    int updateName(@Param("name")String name,@Param("id")Integer id);


}
