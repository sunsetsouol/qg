package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.ShoppingCart;

import java.util.List;

//@Mapper
//@Component("cartMapper")
public interface CartMapper {

    @Select(sql = "select * from cart where user_id = #{userId}")
    List<ShoppingCart> selectById(@Param("userId")Integer userId);

    @Select(sql = "select * from cart where user_id = #{userId} and goods_id = #{goodsId}")
    ShoppingCart selectByUAGId(@Param("userId")Integer userId,@Param("goodsId")Long goodsId);

    @Insert(sql = "insert into cart values(null,#{goodsId},#{number},#{userId})")
    int insert(@Param("goodsId")Long goodsId,@Param("number")Integer number,@Param("userId")Integer userId);

    @Update(sql = "update cart set number = #{number} where id = #{id}")
    int updateNumber(@Param("number")Integer number,@Param("id")Long id);

    @Delete(sql = "delete from cart where id = #{id}")
    int deleteById(@Param("id")Long id);

}
