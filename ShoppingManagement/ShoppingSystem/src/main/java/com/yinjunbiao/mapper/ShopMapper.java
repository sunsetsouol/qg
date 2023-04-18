package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Shop;

import java.util.List;

@Mapper
@Component("shopMapper")
public interface ShopMapper {

    @Select(sql = "select * from shop where name like #{name}")
    List<Shop> selectIdByName(@Param("name")String name);

}
