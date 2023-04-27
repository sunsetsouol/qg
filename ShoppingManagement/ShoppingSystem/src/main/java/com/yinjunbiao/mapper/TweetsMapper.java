package com.yinjunbiao.mapper;

import com.yinjunbiao.MyORM.Annotation.*;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Mapper;
import com.yinjunbiao.entity.Tweets;

import java.util.List;

@Mapper
@Component("tweetsMapper")
public interface TweetsMapper {
    @Insert(sql = "insert into tweets values(null,#{shopId},#{tweet})")
    @ResultMap(id = "tweetsResultMap")
    int insert(@Param("shopId") Integer shopId, @Param("tweet")String tweet);

    @Delete(sql = "delete from tweets where id = #{id}")
    @ResultMap(id = "tweetsResultMap")
    int deleteById(@Param("id")Insert id);


    @Select(sql = "select * from tweets where shop_id = #{shopId}")
    @ResultMap(id = "tweetsResultMap")
    List<Tweets> selectByShopId(@Param("shopId")Integer shopId);

}
