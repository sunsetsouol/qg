package com.yinjunbiao.web;


import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.PushGood;
import com.yinjunbiao.mapper.OrdersMapper;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.ShopOrders;
import com.yinjunbiao.service.OrdersService;
import com.yinjunbiao.service.ShopService;
import com.yinjunbiao.util.ApplicationUtil;
import com.yinjunbiao.util.JwtUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/shop/*")
@Scope("singleton")
@MultipartConfig
public class ShopServlet extends BaseServlet {

    private OrdersService ordersService = ((OrdersService) ApplicationUtil.getApplicationContext().getBean("ordersService"));

    private ShopService shopService = ((ShopService) ApplicationUtil.getApplicationContext().getBean("shopService"));


    public void selectOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Integer status = Integer.valueOf(request.getParameter("status"));
            Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
            Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
            Integer shopId = Integer.valueOf(request.getParameter("shopId"));
            ResultSet resultSet = ordersService.selectOrdersByShopId(shopId,status,currentPage,pageSize);
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        } catch (Exception e) {
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    public void addPush(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        PushGood pushGood = JSON.parseObject(s, PushGood.class);
        ResultSet resultSet = shopService.addPush(pushGood);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    public void send(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        ShopOrders shopOrders = JSON.parseObject(s, ShopOrders.class);
        ResultSet resultSet = ordersService.send(shopOrders);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

}
