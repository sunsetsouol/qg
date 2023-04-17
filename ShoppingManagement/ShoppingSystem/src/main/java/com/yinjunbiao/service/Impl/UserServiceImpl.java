package com.yinjunbiao.service.Impl;


import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.User;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.UserService;
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
        User select = userMapper.select(user.getPhone());
        ResultSet resultSet = null;
        if (select == null) {
            resultSet = ResultSet.error("userName", "账号不存在");
        } else {
            int cnt = select.getCnt();
            if (cnt > 0) {
                synchronized (UserService.class) {
                    cnt = userMapper.select(user.getPhone()).getCnt();
                    if (cnt > 0) {
                        select = userMapper.select(user.getPhone());
                        if (select != null && select.getPassword().equals(user.getPassword())) {
                            resultSet = ResultSet.success(select, "登录成功");
                        } else {
                            resultSet = ResultSet.error(select, "账号不存在或密码错误");
                            userMapper.updateCnt(userMapper.select(user.getPhone()).getCnt() - 1, user.getPhone());
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
}
