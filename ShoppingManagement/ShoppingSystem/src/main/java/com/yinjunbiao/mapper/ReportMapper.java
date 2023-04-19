package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Insert;
import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MyORM.Annotation.Update;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Report;

import java.util.List;

@Mapper
@Component("reportMapper")
public interface ReportMapper {

    @Select(sql = "select * from report")
    List<Report> select();

    @Update(sql = "update report set status = #{status} where id = #{id}")
    int updateStatus(@Param("status")Integer status,@Param("id")Integer id);

    @Insert(sql = "insert into report values(null,#{goodsId},#{userId},0,#{status},#{description}")
    int insert(@Param("goodsId")Long goodsId,@Param("userId")Insert userId,@Param("status")Integer status,@Param("description")String description);

}
