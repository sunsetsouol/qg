package com.yinjunbiao.web;

import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.ComponentScan;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.User;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.UserService;
import com.yinjunbiao.util.ApplicationUtil;
import com.yinjunbiao.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
@ComponentScan("com.yinjunbiao")
@Scope("singleton")
public class UserServlet extends BaseServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger("UserServlet.class");

    @Autowired
    private UserService service = (UserService) ApplicationUtil.getApplicationContext().getBean("service");

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获取数据转换成user对象，如果数据是空直接返回
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        LOGGER.info(s);
        User user = JSON.parseObject(s, User.class);
        if(user == null){
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResultSet.error()));
        }
        LOGGER.info(user.toString());
        //查找并判断密码是否正确
        ResultSet resultSet = service.login(user);
        if (resultSet.getCode() == 1){
            Map<String ,Object> claims = new HashMap<>();
            claims.put("id", ((User) resultSet.getData()).getId());
            String jwt = JwtUtil.generateJwt(claims);
            resultSet.setData(jwt);
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //读取json数据转换成user，并判断是否为空
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        System.out.println(s);
        if(s == null || s.length() == 0){
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResultSet.error("user","参数传递失败")));
            return;
        }
        User user = JSON.parseObject(s, User.class);

        ResultSet resultSet = service.register(user);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));

    }

    public void forget(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        if(s == null || s.length() == 0){
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResultSet.error("user","参数传递失败")));
            return;
        }
        User user = JSON.parseObject(s, User.class);
        ResultSet resultSet = service.changePassword(user);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

}
