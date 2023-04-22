import com.yinjunbiao.mapper.CartMapper;
import com.yinjunbiao.util.CONST;
import com.yinjunbiao.util.SqlSessionUtil;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TestCartMapper {
    @Test
    public void testInsert(){
//        CartMapper mapper = SqlSessionUtil.getSqlSession().getMapper(CartMapper.class);
//        mapper.insert(1L,1,1,1);
//        SqlSessionUtil.commit();
        System.out.println(CONST.dateFormat.format(1682081925383L));
    }
}
