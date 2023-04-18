import com.yinjunbiao.MyORM.SqlSession.SqlSession;
import com.yinjunbiao.entity.Goods;
import com.yinjunbiao.entity.User;
import com.yinjunbiao.mapper.GoodsMapper;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.util.SqlSessionUtil;
import org.junit.Test;

import java.util.List;

public class TestMapper {
    @Test
    public void testUserMapper(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        User select = mapper.select();
//        System.out.println(select);
    }

    @Test
    public void testGoodsMapper(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        List<Goods> goods = mapper.selectAll();
        System.out.println(goods);
    }
}
