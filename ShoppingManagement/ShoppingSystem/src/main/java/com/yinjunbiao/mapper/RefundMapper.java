package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Refund;

import java.util.List;

@Mapper
@Component("refundMapper")
public interface RefundMapper {

    @Select(sql = "select * from refund order by time desc")
    @ResultMap(id = "refundResultMap")
    List<Refund> select();


    @Select(sql = "select * from refund where order_id = #{orderId} and status = 0 ")
    @ResultMap(id = "refundResultMap")
    Refund selectApplyingByOrderId(@Param("orderId")Long orderId);

    @Select(sql = "select * from refund where status = 1 and shop_id = #{shopId} order by time desc")
    @ResultMap(id = "refundResultMap")
    Refund selectSuccessByShopId(@Param("shopId")Integer shopId);

    @Select(sql = "select * from refund where status = 2 and shop_id = #{shopId} order by time desc")
    @ResultMap(id = "refundResultMap")
    Refund selectFailedByShopId(@Param("shopId")Integer shopId);

    @Select(sql = "select * from refund where status = 0 and shop_id = #{shopId} order by time desc limit #{begin}, #{size}")
    @ResultMap(id = "refundResultMap")
    List<Refund> selectApplyingByShopId(@Param("shopId")Integer shopId,@Param("begin")Integer begin,@Param("size")Integer size);

    @Insert(sql = "insert into refund values(null,#{orderedId},#{cause},0,#{description},#{time},#{shopId})")
    @ResultMap(id = "refundResultMap")
    int insert(@Param("orderedId")Long orderedId,@Param("cause")Integer cause,@Param("description")String description,@Param("time")Long time,@Param("shopId")Integer shopId);

    @Update(sql = "update refund set status = #{status} where id = #{id}")
    @ResultMap(id = "refundResultMap")
    int updateStatus(@Param("status")Integer status,@Param("id")Long id);

    @Update(sql = "update refund set description = #{description} where id = #{id}")
    @ResultMap(id = "refundResultMap")
    int updateDesc(@Param("description")String description,@Param("id")Long id);

    @Update(sql = "update refund set cause = #{cause} where id = #{id}")
    @ResultMap(id = "refundResultMap")
    int updateCause(@Param("cause")Integer cause,@Param("id")Long id);

    @Delete(sql = "delete from refund where order_id = #{orderId}")
    @ResultMap(id = "refundResultMap")
    int deleteByOrderId(@Param("orderId")Long orderId);

}
