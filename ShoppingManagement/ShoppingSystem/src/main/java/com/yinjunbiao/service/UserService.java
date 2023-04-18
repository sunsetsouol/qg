package com.yinjunbiao.service;


import com.yinjunbiao.entity.User;
import com.yinjunbiao.pojo.ResultSet;

public interface UserService {

    ResultSet login(User user);

    ResultSet register(User user);

    ResultSet changePassword(User user);
}
