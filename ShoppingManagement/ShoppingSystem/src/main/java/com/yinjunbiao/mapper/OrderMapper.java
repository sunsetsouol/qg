package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Insert;
import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MyORM.Annotation.Update;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Orders;

import java.util.List;

@Mapper
@Component("orderMapper")
public interface OrderMapper {
    @Insert(sql = "insert into orders values(null,#{time},#{sendAddress},#{receiveAddress},#{goodsId},#{userId},0,null,#{number})")
    int insert(@Param("time")Long time,@Param("sendAddress")String sendAddress,@Param("receiveAddress")String receiveAddress,@Param("goodsId")Long goodsId,@Param("userId")Integer id,@Param("number")Integer number);

    @Update(sql = "update orders set status = #{status} where id = #{id}")
    int updateStatus(@Param("status")Integer status,@Param("id")Long id);

    @Update(sql = "update orders set comment = #{comment} where id = #{id}")
    int updateStatus(@Param("comment")String comment,@Param("id")Long id);

    @Select(sql = "select * from orders where id = #{id}")
    Orders select(@Param("id")Long id);

    @Select(sql = "select * from orders where user_id = #{userId}")
    List<Orders> selectByUserId(@Param("userId")Integer userId);





}
