import com.yinjunbiao.entity.User;
import com.yinjunbiao.mapper.CartMapper;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.util.ApplicationUtil;
import com.yinjunbiao.util.CONST;
import com.yinjunbiao.util.Md5Util;
import com.yinjunbiao.util.SqlSessionUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class TestCartMapper {
    @Test
    public void testInsert(){
//        CartMapper mapper = SqlSessionUtil.getSqlSession().getMapper(CartMapper.class);
//        mapper.insert(1L,1,1,1);
//        SqlSessionUtil.commit();
//        System.out.println(CONST.dateFormat.format(1682081925383L));
//        String rawPassword = "aaa";
//        String encodedPassword = rawPassword;
//        String salt = UUID.randomUUID().toString().replace("-","");
//        for (int i = 0; i < 5; i++) {
//            encodedPassword = DigestUtils.md5Hex((salt+encodedPassword+salt+encodedPassword+salt).getBytes());
//        }
//        encodedPassword = salt + encodedPassword;
//        System.out.println(encodedPassword);
//
//        salt = encodedPassword.substring(0,32);
//        String newPassword = rawPassword;
//        for (int i = 0; i < 5; i++) {
//            newPassword = DigestUtils.md5Hex((salt+newPassword+salt+newPassword+salt).getBytes());
//        }
//        newPassword = salt+newPassword;
//        System.out.println(newPassword.equals(encodedPassword));
//
//        Random random = new Random();
//        String rawPassword = "";
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 5; j++) {
//                rawPassword += String.valueOf(random.nextInt());
//            }
//            String encode = Md5Util.encode(rawPassword);
//            System.out.println(Md5Util.matches(rawPassword, encode));
//        }

    }

}
