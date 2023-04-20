package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Insert;
import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MyORM.Annotation.Update;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Apply;

import java.util.List;

@Mapper
@Component("applyMapper")
public interface ApplyMapper {

    @Select(sql = "select * from toshop")
    List<Apply> select();

    @Select(sql = "select * from toshop where user_id = #{userId}")
    Apply selectByUserId(@Param("userId")Integer id);

    @Insert(sql = "insert into toshop values(null,#{userId},0)")
    int insert (@Param("userId")Integer userId);

    @Update(sql = "update toshop set status = #{status}")
    int updateStatus(@Param("status")Integer status);


}
