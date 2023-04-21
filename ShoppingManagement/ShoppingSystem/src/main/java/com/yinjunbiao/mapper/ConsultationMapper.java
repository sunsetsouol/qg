package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Consultation;

import java.util.List;

@Mapper
@Component("consultationMapper")
public interface ConsultationMapper {

    @Select(sql = "select * from consultation where user_id = #{userId}")
    @ResultMap(id = "consultationResultMap")
    List<Consultation> selectByUserId(@Param("userId")Integer userId);

    @Select(sql = "select * from consultation where goods_id = #{goodsId}")
    @ResultMap(id = "consultationResultMap")
    List<Consultation> selectByGoodsId(@Param("userId")Long goodsId);

    @Insert(sql = "insert into consultation values (null,#{goodsId},#{consultation},#{userId})")
    @ResultMap(id = "consultationResultMap")
    int insert(@Param("goodsId")Long goodsId,@Param("consultation")String consultation,@Param("userId")Integer userId);

    @Delete(sql = "delete from consultation where id = #{id}")
    @ResultMap(id = "consultationResultMap")
    int deleteById(@Param("id")Long id);

}
