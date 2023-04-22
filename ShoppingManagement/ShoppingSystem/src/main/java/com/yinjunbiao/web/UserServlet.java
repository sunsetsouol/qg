package com.yinjunbiao.web;

import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.ComponentScan;
import com.yinjunbiao.MySpring.Annotation.Scope;

import com.yinjunbiao.entity.Apply;
import com.yinjunbiao.entity.Cart;
import com.yinjunbiao.entity.Orders;
import com.yinjunbiao.entity.User;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.GoodsService;
import com.yinjunbiao.service.UserService;
import com.yinjunbiao.util.ApplicationUtil;
import com.yinjunbiao.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
@ComponentScan("com.yinjunbiao")
@Scope("singleton")
@MultipartConfig
public class UserServlet extends BaseServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger("UserServlet.class");

    @Autowired
    private UserService userService = (UserService) ApplicationUtil.getApplicationContext().getBean("userService");

    @Autowired
    private GoodsService goodsService;

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获取数据转换成user对象，如果数据是空直接返回
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        LOGGER.info(s);
        User user = JSON.parseObject(s, User.class);
        if (user == null) {
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResultSet.error()));
        }
        System.out.println(user);
        LOGGER.info(user.toString());
        //查找并判断密码是否正确
        ResultSet resultSet = userService.login(user);

        if (resultSet.getCode() == 1) {
            Map<String, Object> claims = new HashMap<>();

            claims.put("id", ((User) resultSet.getData()).getId());
            String jwt = JwtUtil.generateJwt(claims);
            resultSet.setData(jwt);
        }
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 注册
     * @param request
     * @param response
     * @throws Exception
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //读取json数据转换成user，并判断是否为空
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        System.out.println(s);
        if (s == null || s.length() == 0) {
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResultSet.error("user", "参数传递失败")));
            return;
        }
        User user = JSON.parseObject(s, User.class);

        ResultSet resultSet = userService.register(user);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));

    }

    /**
     * 忘记密码
     * @param request
     * @param response
     * @throws IOException
     */
    public void forget(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        if (s == null || s.length() == 0) {
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResultSet.error("user", "参数传递失败")));
            return;
        }
        User user = JSON.parseObject(s, User.class);
        ResultSet resultSet = userService.changePassword(user);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 直接购买
     * @param request
     * @param response
     * @throws IOException
     */
    public void buy(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String token = request.getHeader("Authorization");
        try {
            Claims claims = JwtUtil.parseJWT(token);
            Integer id = (Integer) claims.get("id");
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Orders orders = JSON.parseObject(s, Orders.class);
            orders.setUserId(id);
            ResultSet resultSet = userService.newOrders(orders);
            if (resultSet.getCode() == 1){
                response.setStatus(200);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(resultSet));
            }
        }catch (Exception e){
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 添加到购物车
     * @param request
     * @param response
     * @throws IOException
     */
    public void addCart(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String token = request.getHeader("Authorization");
        try{
            Claims claims = JwtUtil.parseJWT(token);
            Integer id = (Integer) claims.get("id");
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Cart cart = JSON.parseObject(s, Cart.class);
            cart.setUserId(id);
            ResultSet resultSet = userService.addCart(cart);
            if (resultSet.getCode() == 1){
                response.setStatus(200);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(resultSet));
            }
        }catch (Exception e){
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 申请称为商家
     * @param request
     * @param response
     * @throws IOException
     */
    public void apply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Apply apply = JSON.parseObject(s, Apply.class);
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");

            apply.setUserId(id);
            ResultSet resultSet = userService.applyShop(apply);
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        }catch (Exception e){
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 查询购物车
     * @param request
     * @param response
     * @throws IOException
     */
    public void searchMyCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");
            ResultSet resultSet = userService.selectMyShoppingCart(id);
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        }catch (Exception e){
            response.sendRedirect("/ShoppingSystem/login.html");

        }
    }

    /**
     * 查询自己的店铺
     * @param request
     * @param response
     * @throws IOException
     */
    public void myShop(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");
            ResultSet resultSet = userService.selectMyShop(id);
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        }catch (Exception e){
            response.sendRedirect("/ShoppingSystem/login.html");

        }
    }

}
