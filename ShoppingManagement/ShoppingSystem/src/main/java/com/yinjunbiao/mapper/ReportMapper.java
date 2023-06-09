package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Report;

import java.util.List;

@Mapper
@Component("reportMapper")
public interface ReportMapper {

    @Select(sql = "select * from report where status = 0")
    @ResultMap(id = "reportResultMap")
    List<Report> select();

    @Select(sql = "select * from report where user_id = #{userId} and goods_id = #{goodsId} and status = 0")
    @ResultMap(id = "reportResultMap")
    Report selectByUAGId(@Param("userId")Integer userId,@Param("goodsId")Long goodsId);

    @Update(sql = "update report set status = #{status} where goods_id = #{goodsId}")
    @ResultMap(id = "reportResultMap")
    int updateStatusByGoodsId(@Param("status")Integer status,@Param("goodsId")Long goodsId);

    @Update(sql = "update report set status = #{status} where id = #{id}")
    @ResultMap(id = "reportResultMap")
    int updateStatusById(@Param("status")Integer status,@Param("id")Long id);

    @Insert(sql = "insert into report values(null,#{goodsId},#{userId},0,#{description})")
    @ResultMap(id = "reportResultMap")
    int insert(@Param("goodsId")Long goodsId,@Param("userId")Integer userId,@Param("status")Integer status,@Param("description")String description);

}
