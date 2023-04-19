package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Delete;
import com.yinjunbiao.MyORM.Annotation.Insert;
import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Select;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Reply;

import java.util.List;

@Mapper
@Component("replyMapper")
public interface ReplyMapper {

    @Select(sql = "select * from reply where consultation_id = #{consultationId}")
    List<Reply> selectByCId(@Param("consultationId")Long consultationId);

    @Select(sql = "select * from reply where user_id = #{userId}")
    List<Reply> selectByUId(@Param("userId")Integer userId);

    @Insert(sql = "insert into reply values(null,#{consultationId},#{reply},#{userId})")
    int insert(@Param("consultationId")Long consultationId,@Param("reply")String reply,@Param("userId")Integer userId);

    @Delete(sql = "delete from reply where id = #{id}")
    int deleteById(@Param("id")Long id);

}
