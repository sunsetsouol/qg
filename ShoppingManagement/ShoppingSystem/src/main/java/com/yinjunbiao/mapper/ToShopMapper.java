package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Insert;
import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MyORM.Annotation.Update;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.ToShop;

import java.util.List;

//@Mapper
//@Component("toShopMapper")
public interface ToShopMapper {

    @Select(sql = "select * from toshop")
    List<ToShop> select();

    @Select(sql = "select * from toshop where user_id = #{userId}")
    ToShop selectByUserId(@Param("userId")Integer id);

    @Insert(sql = "insert into toshop values(null,#{userId},0)")
    int insert (@Param("userId")Integer userId);

    @Update(sql = "update toshop set status = #{status}")
    int updateStatus(@Param("status")Integer status);


}
