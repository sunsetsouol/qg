package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MyORM.SqlSession.SqlSession;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.User;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.UserService;
import com.yinjunbiao.util.SqlSessionUtil;

import java.util.TimerTask;
import java.util.concurrent.*;

@Component("service")
@Scope("singleton")
public class UserServiceImpl implements UserService {

    @Override
    public ResultSet login(User user) {
        return null;
    }
}
