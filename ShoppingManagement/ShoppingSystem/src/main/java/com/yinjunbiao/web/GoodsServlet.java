package com.yinjunbiao.web;

import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.ComponentScan;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.Goods;
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

    public void selectByName(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Goods goods = JSON.parseObject(s, Goods.class);
        String token = request.getHeader("Authorization");
        Claims claims = JwtUtil.parseJWT(token);
        ResultSet resultSet = goodsService.selectByName(goods.getName(),currentPage,pageSize);
        System.out.println(claims.get("id"));
        response.setStatus(200);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }
}