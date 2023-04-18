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
        User user = mapper.selectByPhone("12312312312");
        System.out.println(user);
        int i = mapper.updatePhone("12312312312", "12312");
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        System.out.println(i);
    }

    @Test
    public void testGoodsMapper(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        List<Goods> goods = mapper.selectAll();
        System.out.println(goods);
    }
}
