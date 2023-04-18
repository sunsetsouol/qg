package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Goods;

import java.util.List;

//@Mapper
//@Component("goodsMapper")
public interface GoodsMapper {

    @Select(sql = "select * from goods")
    List<Goods> selectAll();

}
