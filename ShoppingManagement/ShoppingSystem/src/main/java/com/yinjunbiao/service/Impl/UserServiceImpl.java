package com.yinjunbiao.service.Impl;


import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.User;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.UserService;
import com.yinjunbiao.util.CONST;
import com.yinjunbiao.util.SqlSessionUtil;

@Component("service")
@Scope("singleton")
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
    }

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultSet login(User user) {
        User select = userMapper.selectByPhone(user.getPhone());
        ResultSet resultSet = null;
        if (select == null) {
            resultSet = ResultSet.error("userName", "账号不存在");
        } else {
            int cnt = select.getCnt();
            if (cnt > 0) {
                synchronized (UserService.class) {
                    cnt = userMapper.selectByPhone(user.getPhone()).getCnt();
                    if (cnt > 0) {
                        select = userMapper.selectByPhone(user.getPhone());
                        if (select != null && select.getPassword().equals(user.getPassword())) {
                            resultSet = ResultSet.success(select, "登录成功");
                        } else {
                            resultSet = ResultSet.error(select, "账号不存在或密码错误");
                            userMapper.updateCnt(userMapper.selectByPhone(user.getPhone()).getCnt() - 1, user.getPhone());
                            SqlSessionUtil.commit();
                            SqlSessionUtil.close();
                        }
                    }
                }
            } else {
                resultSet = ResultSet.error(null, "登录失败次数过多，请稍后重试");
            }
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }

    @Override
    public ResultSet register(User user) {
        ResultSet resultSet = null;
        for (String s : CONST.SENSITIVE) {
            if (user.getUserName().contains(s)) {
                resultSet = ResultSet.error("userName", "用户名含有敏感字");
            }
        }
            int res;
            if (userMapper.selectByPhone(user.getUserName()) != null) {
                resultSet = ResultSet.error("userName", "用户名已被注册");
            }else {
                synchronized (UserServiceImpl.class) {
                    if (userMapper.selectByPhone(user.getPhone()) != null) {
                        resultSet = ResultSet.error("userName", "用户名已被注册");
                    } else if (user.getUserName().length() > 20 || user.getPassword().length() > 20 || user.getAddress().length() > 50) {
                        resultSet = ResultSet.error(null, "账号跟密码长度不能超过20个字符,地址不能超过五十个字符");
                    } else {
                        res = userMapper.add(user.getPhone(),user.getUserName(),user.getAddress(),user.getPassword(),user.getIsPrivate());
                        if (res == 1) {
                            resultSet = ResultSet.success(res, "注册成功");
                        }
                    }
                }
            }

        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }

    @Override
    public ResultSet changePassword(User user) {
        User select = userMapper.selectByPhone(user.getPhone());
        ResultSet resultSet = null;
        int res = 0;
        if (select == null) {
            resultSet = ResultSet.error("phone", "该手机号尚未注册");
        } else if (select.getPassword().equals(user.getPassword())) {
            resultSet = ResultSet.error("password", "不能修改与原来相同的密码");
        } else {
            res = userMapper.updatePassword(user.getPassword(), user.getPhone());
        }
        if (res == 1) {
            resultSet = ResultSet.success(null, "修改成功");
        }

        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }
}
