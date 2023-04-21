import com.yinjunbiao.mapper.CartMapper;
import com.yinjunbiao.util.SqlSessionUtil;
import org.junit.Test;

public class TestCartMapper {
    @Test
    public void testInsert(){
        CartMapper mapper = SqlSessionUtil.getSqlSession().getMapper(CartMapper.class);
        mapper.insert(1L,1,1,1);
        SqlSessionUtil.commit();

    }
}
