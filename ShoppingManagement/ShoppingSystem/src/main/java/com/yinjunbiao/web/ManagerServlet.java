package com.yinjunbiao.web;

import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.*;
import com.yinjunbiao.pojo.GoodsConsultations;
import com.yinjunbiao.pojo.GoodsReply;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.GoodsService;
import com.yinjunbiao.service.ManagerService;
import com.yinjunbiao.util.ApplicationUtil;
import com.yinjunbiao.util.JwtUtil;
import io.jsonwebtoken.Claims;


import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/manager/*")
@Scope("singleton")
@MultipartConfig
public class ManagerServlet extends BaseServlet{

    private GoodsService goodsService = ((GoodsService) ApplicationUtil.getApplicationContext().getBean("goodsService"));

    private ManagerService managerService = ((ManagerService) ApplicationUtil.getApplicationContext().getBean("managerService"));

    /**
     * 管理员入口
     * @param request
     * @param response
     * @throws IOException
     */
    public void entrance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        try {
            Claims claims = JwtUtil.parseJWT(token);
            Integer identify = (Integer) claims.get("identify");
            ResultSet resultSet = null;
            if (identify == 2){
                resultSet = ResultSet.success();
            }else {
                resultSet = ResultSet.error();
            }
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        }catch (Exception e){
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 删除商品
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Goods goods = JSON.parseObject(s, Goods.class);
        ResultSet resultSet = managerService.deleteGoods(goods.getId());
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }
    /**
     * 同意下架商品
     * @param request
     * @param response
     * @throws IOException
     */
    public void agreeReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Report report = JSON.parseObject(s, Report.class);
        ResultSet resultSet = managerService.deleteGoods(report.getGoodsId());
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }
    /**
     * 删除回复
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteReply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        GoodsReply goodsReply = JSON.parseObject(s, GoodsReply.class);
        ResultSet resultSet = managerService.deleteReply(goodsReply);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 删除评论
      * @param request
     * @param response
     * @throws IOException
     */
    public void deleteAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        GoodsConsultations goodsConsultations = JSON.parseObject(s, GoodsConsultations.class);
        ResultSet resultSet = managerService.deleteConsultations(goodsConsultations);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 查找店铺申请
     * @param request
     * @param response
     * @throws IOException
     */
    public void shopApply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultSet resultSet = managerService.selectShopApply();
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 同意店铺申请
     * @param request
     * @param response
     * @throws IOException
     */
    public void agreeShop(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Apply apply = JSON.parseObject(s, Apply.class);
        ResultSet resultSet = managerService.agreeShopApply(apply);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 拒绝店铺申请
     * @param request
     * @param response
     * @throws IOException
     */
    public void disagreeShop(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Apply apply = JSON.parseObject(s, Apply.class);
        ResultSet resultSet = managerService.disagreeShopApply(apply);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 查看商品商家申请
     * @param request
     * @param response
     * @throws IOException
     */
    public void pushApply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultSet resultSet = managerService.selectPushApply();
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 同意推送商品
     * @param request
     * @param response
     * @throws IOException
     */
    public void agreeGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        PushGood pushGood = JSON.parseObject(s, PushGood.class);
        ResultSet resultSet = managerService.agreePushGoods(pushGood);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 拒绝推送商品
     * @param request
     * @param response
     * @throws IOException
     */
    public void disagreeGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        PushGood pushGood = JSON.parseObject(s, PushGood.class);
        ResultSet resultSet = managerService.disagreePushGoods(pushGood);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }
    /**
     * 查找举报记录
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectReports(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResultSet resultSet = managerService.selectReport();
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }


    /**
     * 不同意下架商品
     * @param request
     * @param response
     * @throws IOException
     */
    public void disagreeReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Report report = JSON.parseObject(s, Report.class);
        ResultSet resultSet = managerService.disagreeReport(report);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }


}
