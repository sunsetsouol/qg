package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Insert;
import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MyORM.Annotation.Update;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.PushGood;

import java.util.List;

@Mapper
@Component("pushGoodsMapper")
public interface PushGoodsMapper {

    @Insert(sql = "insert into push values(null,#{name},#{price},0,#{picture})")
    int insert(@Param("name")String name,@Param("price")Integer price,@Param("picture")String picture);

    @Select(sql = "select * from push")
    List<PushGood> select();

    @Select(sql = "select * from push where id = #{id}")
    PushGood selectById(@Param("id")Long id);

    @Select(sql = "select * from push where name like #{name}")
    List<PushGood> selectByName(@Param("name")String name);

    @Select(sql = "select * from push where status = #{status}")
    List<PushGood> selectByStatus(@Param("status")Integer status);

    @Update(sql = "update push set name = #{name} where id = #{id}")
    int updateName(@Param("name")String name,@Param("id")Long id);

    @Update(sql = "update push set price = #{price} where id = #{id}")
    int updatePrice(@Param("price")Integer price,@Param("id")Long id);

    @Update(sql = "update push set status = #{status} where id = #{id}")
    int updateStatus(@Param("status")Integer status,@Param("id")Long id);

    @Update(sql = "update push set picture = #{picture} where id = #{id}")
    int updatePicture(@Param("picture")String picture,@Param("id")Long id);
}
