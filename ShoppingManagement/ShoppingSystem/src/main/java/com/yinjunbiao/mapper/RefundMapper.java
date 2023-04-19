package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Insert;
import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MyORM.Annotation.Update;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Refund;

import java.util.List;

//@Mapper
//@Component("refundMapper")
public interface RefundMapper {

    @Select(sql = "select * from refund")
    List<Refund> select();

    @Select(sql = "select * from refund where order_id = #{orderId}")
    List<Refund> selectByOrderId(@Param("orderId")Long orderId);

    @Select(sql = "select * from refund where status = 1 order_id = #{orderId}")
    Refund selectSuccessByOrderId(@Param("orderId")Long orderId);

    @Select(sql = "select * from refund where status = 2 order_id = #{orderId}")
    Refund selectFailedByOrderId(@Param("orderId")Long orderId);

    @Insert(sql = "insert into refund values(null,#{orderedId},#{cause},0,#{description})")
    int insert(@Param("orderedId")Long orderedId,@Param("cause")Integer cause,@Param("description")String description);

    @Update(sql = "update refund set status = #{status} where id = #{id}")
    int updateStatus(@Param("status")Integer status,@Param("id")Long id);

    @Update(sql = "update refund set description = #{description} where id = #{id}")
    int updateDesc(@Param("description")String description,@Param("id")Long id);

    @Update(sql = "update refund set cause = #{cause} where id = #{id}")
    int updateCause(@Param("cause")Integer cause,@Param("id")Long id);

}
