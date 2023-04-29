import com.yinjunbiao.entity.User;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.util.JwtUtil;
import com.yinjunbiao.util.SqlSessionUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUserMapper {
    private UserMapper mapper = SqlSessionUtil.getSqlSession().getMapper(UserMapper.class);
    @Test
    public void testSelectById(){

        User user = mapper.selectById(1);
        System.out.println(user);
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
    }
    @Test
    public void testSelectByPhone(){

        User user = mapper.selectByPhone("0");
        System.out.println(user);
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
    }

    @Test
    public void testSelectByName(){
        List<User> users = mapper.selectByName("a");
        System.out.println(users);
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
    }

    @Test
    public void testInsert(){
//        mapper.insert("1245","123","123","123",0);
//        SqlSessionUtil.commit();
//        SqlSessionUtil.close();
    }

    @Test
    public void testUpdatePassword(){
        mapper.updatePassword("xll",1);
        SqlSessionUtil.commit();
    }

    @Test
    public void testUpdatePhone(){
        mapper.updatePhone("1",1);
        SqlSessionUtil.commit();
    }

    @Test
    public void testUpdateIdentify(){
        mapper.updateIdentify(1,1);
        SqlSessionUtil.commit();
    }
    @Test
    public void testUpdateUserName(){
        mapper.updateUserName("aaa",1);
        SqlSessionUtil.commit();
    }

    @Test
    public void testUpdateIsPrivate(){
        mapper.updateIsPrivate(1,1);
        SqlSessionUtil.commit();
    }

    @Test
    public void testUpdateAddress(){
        mapper.updateAddress("aaa",1);
        SqlSessionUtil.commit();
    }

    @Test
    public void testUpdateHeadshot(){
        mapper.updateHeadshot("aaa",1);
        SqlSessionUtil.commit();
    }
}
