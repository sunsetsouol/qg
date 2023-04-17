import com.yinjunbiao.MyORM.SqlSession.SqlSession;
import com.yinjunbiao.entity.User;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.util.SqlSessionUtil;
import org.junit.Test;

public class TestMapper {
    @Test
    public void testMapper(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        User select = mapper.select();
//        System.out.println(select);
    }
}
