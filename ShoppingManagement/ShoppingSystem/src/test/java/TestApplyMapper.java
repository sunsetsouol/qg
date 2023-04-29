import com.yinjunbiao.MyORM.SqlSession.SqlSession;
import com.yinjunbiao.entity.Apply;
import com.yinjunbiao.mapper.ApplyMapper;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.util.ApplicationUtil;
import com.yinjunbiao.util.Md5Util;
import com.yinjunbiao.util.SqlSessionUtil;
import org.junit.Test;

public class TestApplyMapper {
    @Test
    public void testApplyMapper(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ApplyMapper mapper = sqlSession.getMapper(ApplyMapper.class);
        Apply apply = mapper.selectByUserId(3);
        System.out.println(apply);
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ApplyMapper mapper = sqlSession.getMapper(ApplyMapper.class);
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
    }
}
