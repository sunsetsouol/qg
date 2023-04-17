package com.yinjunbiao.web;

import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.ComponentScan;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.User;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.UserService;
import com.yinjunbiao.util.ApplicationUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/*")
@ComponentScan("com.yinjunbiao")
@Scope("singleton")
public class UserServlet extends BaseServlet {



    @Autowired
    private UserService service = (UserService) ApplicationUtil.getApplicationContext().getBean("service");

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取数据转换成user对象，如果数据是空直接返回
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        System.out.println(s);
        User user = JSON.parseObject(s, User.class);
        System.out.println(user);
        //查找并判断密码是否正确
        ResultSet resultSet = service.login(user);
        if (resultSet.getCode() == 1){
            HttpSession session = request.getSession();
            session.setAttribute("user",resultSet.getData());
            resultSet.setData(null);
        }
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }


}
