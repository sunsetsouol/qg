package com.yinjunbiao.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * md5加密工具类
 */
public class Md5Util {

    public static String encode(String rawPassword){
        String encodedPassword = rawPassword;
        String salt = UUID.randomUUID().toString().replace("-","");
        for (int i = 0; i < 5; i++) {
            encodedPassword = DigestUtils.md5Hex((salt+encodedPassword+salt+encodedPassword+salt).getBytes());
        }
        return salt + encodedPassword;
    }

    public static boolean matches(String rawPassword, String encodedPassword){
        String salt = encodedPassword.substring(0,32);
        String newPassword = rawPassword;
        for (int i = 0; i < 5; i++) {
            newPassword = DigestUtils.md5Hex((salt+newPassword+salt+newPassword+salt).getBytes());
        }
        newPassword = salt+newPassword;
        return newPassword.equals(encodedPassword);
    }
}
