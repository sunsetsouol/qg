package com.yinjunbiao.web;

import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.ComponentScan;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.Cart;
import com.yinjunbiao.entity.Goods;
import com.yinjunbiao.entity.Orders;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.GoodsService;
import com.yinjunbiao.util.ApplicationUtil;
import com.yinjunbiao.util.JwtUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/goods/*")
@Scope("singleton")
public class GoodsServlet extends BaseServlet {

    @Autowired
    private GoodsService goodsService = (GoodsService) ApplicationUtil.getApplicationContext().getBean("goodsService");

    /**
     * 查询商品
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        ResultSet resultSet = goodsService.selectByPage(currentPage, pageSize);
        if (resultSet.getCode() == 1){
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        }
    }

    /**
     * 模糊搜索
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectByName(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Goods goods = JSON.parseObject(s, Goods.class);
        String token = request.getHeader("Authorization");
        Claims claims = JwtUtil.parseJWT(token);
        ResultSet resultSet = goodsService.selectByName(goods.getName(),currentPage,pageSize);
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
            System.out.println(orders);
            ResultSet resultSet = goodsService.newOrders(orders);
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
            System.out.println(cart);
            ResultSet resultSet = goodsService.addCart(cart);
            if (resultSet.getCode() == 1){
                response.setStatus(200);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(resultSet));
            }
        }catch (Exception e){
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }
}