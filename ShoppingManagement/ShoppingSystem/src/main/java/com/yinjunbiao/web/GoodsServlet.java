package com.yinjunbiao.web;

import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.*;
import com.yinjunbiao.pojo.GoodsConsultations;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.GoodsService;
import com.yinjunbiao.util.ApplicationUtil;
import com.yinjunbiao.util.JwtUtil;
import com.yinjunbiao.util.SqlSessionUtil;
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
        ResultSet resultSet = goodsService.selectByName(goods.getName(),currentPage,pageSize);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }
    /**
     * 查询商品评价
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectConsultation(HttpServletRequest request, HttpServletResponse response) throws IOException{
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Long id = JSON.parseObject(s, Long.class);
        ResultSet resultSet = goodsService.selectConsultation(id);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }
    /**
     * 发送商品评价
     * @param request
     * @param response
     * @throws IOException
     */
    public void sendConsultation(HttpServletRequest request, HttpServletResponse response) throws IOException{
       try{
           String authorization = request.getHeader("Authorization");
           Claims claims = JwtUtil.parseJWT(authorization);
           Integer id = (Integer) claims.get("id");
           BufferedReader reader = request.getReader();
           String s = reader.readLine();
           Consultation consultation = JSON.parseObject(s, Consultation.class);
           consultation.setUserId(id);
           ResultSet resultSet = goodsService.sendConsultation(consultation);
           response.setStatus(200);
           response.setContentType("application/json;charset=utf-8");
           response.getWriter().write(JSON.toJSONString(resultSet));
       }catch (Exception e){
           SqlSessionUtil.close();
           response.sendRedirect("/ShoppingSystem/login.html");
       }
    }

    /**
     * 查看评价（提问）回复
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectReply(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            GoodsConsultations goodsConsultations = JSON.parseObject(s, GoodsConsultations.class);
            ResultSet resultSet = goodsService.selectReply(goodsConsultations.getId());
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        }catch (Exception e){
            SqlSessionUtil.close();
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 回复商品评价
     * @param request
     * @param response
     * @throws IOException
     */
    public void sendReply(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Reply reply = JSON.parseObject(s, Reply.class);
            reply.setUserId(id);
            ResultSet resultSet = goodsService.sendReply(reply);
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        }catch (Exception e){
            SqlSessionUtil.close();
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }


}