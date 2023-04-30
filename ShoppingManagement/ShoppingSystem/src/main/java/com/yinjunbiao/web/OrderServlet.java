package com.yinjunbiao.web;

import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.Orders;
import com.yinjunbiao.entity.Refund;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.OrdersService;
import com.yinjunbiao.service.UserService;
import com.yinjunbiao.util.ApplicationUtil;
import com.yinjunbiao.util.JwtUtil;
import com.yinjunbiao.util.SqlSessionUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/order/*")
@Scope("singleton")
public class OrderServlet extends BaseServlet{

    @Autowired
    private UserService userService = (UserService) ApplicationUtil.getApplicationContext().getBean("userService");

    @Autowired
    private OrdersService ordersService = ((OrdersService) ApplicationUtil.getApplicationContext().getBean("ordersService"));

    /**
     * 直接购买
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void buy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        try {
            Claims claims = JwtUtil.parseJWT(token);
            Integer id = (Integer) claims.get("id");
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Orders orders = JSON.parseObject(s, Orders.class);
            orders.setUserId(id);
            ResultSet resultSet = ordersService.newOrders(orders);
            if (resultSet.getCode() == 1) {
                response.setStatus(200);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
            SqlSessionUtil.close();
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 确认收货
     * @param request
     * @param response
     * @throws IOException
     */
    public void confirmReceipt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Long id = JSON.parseObject(s, Long.class);
            ResultSet resultSet = ordersService.confirm(id);
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
            SqlSessionUtil.close();
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 申请退款
     * @param request
     * @param response
     * @throws IOException
     */
    public void refund(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Refund refund = JSON.parseObject(s, Refund.class);
            ResultSet resultSet = ordersService.refund(refund);
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
            SqlSessionUtil.close();
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }
}
