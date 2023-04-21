package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Orders;

import java.util.List;

@Mapper
@Component("ordersMapper")
public interface OrdersMapper {
    @Insert(sql = "insert into orders values(null,#{time},#{sendAddress},#{receiveAddress},#{goodsId},#{userId},1,null,#{number},#{singlePrice})")
    @ResultMap(id = "ordersResultMap")
    int insert(@Param("time")Long time,@Param("sendAddress")String sendAddress,@Param("receiveAddress")String receiveAddress,@Param("goodsId")Long goodsId,@Param("userId")Integer userId,@Param("number")Integer number,@Param("singlePrice")Integer singlePrice);

    @Update(sql = "update orders set status = #{status} where id = #{id}")
    @ResultMap(id = "ordersResultMap")
    int updateStatus(@Param("status")Integer status,@Param("id")Long id);

    @Update(sql = "update orders set comment = #{comment} where id = #{id}")
    @ResultMap(id = "ordersResultMap")
    int updateStatus(@Param("comment")String comment,@Param("id")Long id);

    @Select(sql = "select * from orders where id = #{id}")
    @ResultMap(id = "ordersResultMap")
    Orders select(@Param("id")Long id);

    @Select(sql = "select * from orders where user_id = #{userId}")
    @ResultMap(id = "ordersResultMap")
    List<Orders> selectByUserId(@Param("userId")Integer userId);





}