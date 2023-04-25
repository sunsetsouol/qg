package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Cart;

import java.util.List;

@Mapper
@Component("cartMapper")
public interface CartMapper {

    @Select(sql = "select * from cart where user_id = #{userId}")
    @ResultMap(id = "cartResultMap")
    List<Cart> selectByUserId(@Param("userId")Integer userId);

    @Select(sql = "select * from cart where user_id = #{userId} and goods_id = #{goodsId}")
    @ResultMap(id = "cartResultMap")
    Cart selectByUAGId(@Param("userId")Integer userId, @Param("goodsId")Long goodsId);

    @Select(sql = "select * from cart where id = #{id}")
    @ResultMap(id = "cartResultMap")
    Cart selectById(@Param("id")Long id);

    @Insert(sql = "insert into cart values(null,#{goodsId},#{shopId},#{number},#{userId},#{singlePrice})")
    @ResultMap(id = "cartResultMap")
    int insert(@Param("goodsId")Long goodsId,@Param("shopId")Integer shopId,@Param("number")Integer number,@Param("userId")Integer userId,@Param("singlePrice")Integer singlePrice);

    @Update(sql = "update cart set number = #{number} where id = #{id}")
    @ResultMap(id = "cartResultMap")
    int updateNumber(@Param("number")Integer number,@Param("id")Long id);

    @Delete(sql = "delete from cart where id = #{id}")
    @ResultMap(id = "cartResultMap")
    int deleteById(@Param("id")Long id);

    @Delete(sql = "delete from cart where goods_id = #{goodsId}")
    @ResultMap(id = "cartResultMap")
    int deleteByGoodsId(@Param("goodsId")Long goodsId);
}
