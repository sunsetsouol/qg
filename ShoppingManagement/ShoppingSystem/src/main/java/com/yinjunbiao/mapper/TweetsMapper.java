package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;

@Mapper
@Component("tweetsMapper")
public interface TweetsMapper {
    @Insert(sql = "insert into tweet values(null,#{shopId},#{tweet})")
    @ResultMap(id = "tweetsResultMap")
    int insert(@Param("shopId") Integer shopId, @Param("tweet")String tweet);

    @Delete(sql = "delete from tweet where id = #{id}")
    @ResultMap(id = "tweetsResultMap")
    int deleteById(@Param("id")Insert id);

}
