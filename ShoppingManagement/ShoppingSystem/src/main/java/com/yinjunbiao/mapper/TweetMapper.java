package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.Delete;
import com.yinjunbiao.MyORM.Annotation.Insert;
import com.yinjunbiao.MyORM.Annotation.Param;
import com.yinjunbiao.MyORM.Annotation.Update;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;

@Mapper
@Component("tweetmapper")
public interface TweetMapper {
    @Insert(sql = "insert into tweet values(null,#{shopId},#{tweet})")
    int insert(@Param("shopId") Insert shopId, @Param("tweet")String tweet);

    @Delete(sql = "delete from tweet where id = #{id}")
    int deleteById(@Param("id")Insert id);

}
