package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Insert;
import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MyORM.Annotation.Update;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Shop;

import java.util.List;

@Mapper
@Component("shopMapper")
public interface ShopMapper {

    @Select(sql = "select * from shop where name like #{name}")
    List<Shop> selectIdByName(@Param("name")String name);

    @Select(sql = "select * from shop where description like #{description}")
    List<Shop> selectIdByDesc(@Param("description")String description);

    @Select(sql = "select * from shop where id = #{id}")
    Shop selectById(@Param("id")Integer id);

    @Insert(sql = "insert into shop values(null,#{bossId},0,#{introduction},#{sales}")
    int insert(@Param("bossId")Integer bossId,@Param("introduction")String introduction,@Param("sales")Integer sales);

    @Update(sql = "update shop set fans = #{fans} where id = #{id}")
    int updateFans(@Param("fans")Integer fans,@Param("id")Integer id);

    @Update(sql = "update shop set description = #{description} where id = #{id}")
    int updateDesc(@Param("description")String  description,@Param("id")Integer id);

    @Update(sql = "update shop set sales = #{sales} where id = #{id}")
    int updateSales(@Param("sales")Integer sales,@Param("id")Integer id);

    @Update(sql = "update shop set name = #{name} where id = #{id}")
    int updateName(@Param("name")String name,@Param("id")Integer id);


}
