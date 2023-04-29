package com.yinjunbiao.web;


import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.*;
import com.yinjunbiao.mapper.OrdersMapper;
import com.yinjunbiao.pojo.RefundApply;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.ShopOrders;
import com.yinjunbiao.pojo.ShopTweets;
import com.yinjunbiao.service.OrdersService;
import com.yinjunbiao.service.ShopService;
import com.yinjunbiao.util.ApplicationUtil;
import com.yinjunbiao.util.JwtUtil;
import com.yinjunbiao.util.SqlSessionUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/shop/*")
@Scope("singleton")
@MultipartConfig
public class ShopServlet extends BaseServlet {

    private OrdersService ordersService = ((OrdersService) ApplicationUtil.getApplicationContext().getBean("ordersService"));

    private ShopService shopService = ((ShopService) ApplicationUtil.getApplicationContext().getBean("shopService"));


    /**
     * 查询订单
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Integer status = Integer.valueOf(request.getParameter("status"));
            Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
            Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
            Integer shopId = Integer.valueOf(request.getParameter("shopId"));
            ResultSet resultSet = ordersService.selectOrdersByShopId(shopId, status, currentPage, pageSize);
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        } catch (Exception e) {
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 申请添加商品
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void addPush(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        PushGood pushGood = JSON.parseObject(s, PushGood.class);
        ResultSet resultSet = shopService.addPush(pushGood);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 商品已发货
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void send(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        ShopOrders shopOrders = JSON.parseObject(s, ShopOrders.class);
        ResultSet resultSet = null;
        if (shopOrders.getStatus().equals("未发货")) {
            resultSet = ordersService.send(shopOrders);
        } else {
            resultSet = ResultSet.error();
        }
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }


    /**
     * 查看退款申请
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectRefund(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        Integer shopId = Integer.valueOf(request.getParameter("shopId"));
        ResultSet resultSet = shopService.selectRefund(shopId,currentPage,pageSize);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 同意退款
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void agree(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        RefundApply refundApply = JSON.parseObject(s, RefundApply.class);
        ResultSet resultSet = shopService.agree(refundApply);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 拒绝退款
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void disagree(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        RefundApply refundApply = JSON.parseObject(s, RefundApply.class);
        ResultSet resultSet = shopService.disagree(refundApply);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 发送推文
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void sendTweets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Tweets tweets = JSON.parseObject(s, Tweets.class);
        ResultSet resultSet = shopService.sendTweets(tweets);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 查找店铺
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void searchShop(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Shop shop = JSON.parseObject(s, Shop.class);
        ResultSet resultSet = shopService.searchShop(shop);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 查找店铺商品
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void searchShopGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Shop shop = JSON.parseObject(s, Shop.class);
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        ResultSet resultSet = shopService.searchShopGoods(shop.getId(), currentPage, pageSize);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 查找店铺推送商品
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void goodsPush(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer shopId = Integer.valueOf(request.getParameter("shopId"));
        ResultSet resultSet = shopService.searchPushingGoods(shopId);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 修改图片
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void changePicture(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Part imageUrl = request.getPart("file");
        InputStream inputStream = imageUrl.getInputStream();
        try (inputStream){
            Long pushId = Long.valueOf(request.getParameter("pushId"));
            ResultSet resultSet = shopService.changePicture(inputStream, pushId);
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        }
    }

    /**
     * 查看店铺推文
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectTweets(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer shopId = Integer.valueOf(request.getParameter("shopId"));
        ResultSet resultSet = shopService.selectTweets(shopId);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 删除店铺推文
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteTweets(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        ShopTweets shopTweets = JSON.parseObject(s, ShopTweets.class);
        ResultSet resultSet = shopService.deleteTweets(shopTweets);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }
    /**
     * 查看商店信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectMessages(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Integer shopId = Integer.valueOf(request.getParameter("shopId"));
            ResultSet resultSet = shopService.selectMessage(shopId);
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
     * 删除店铺信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            ShopMessage shopMessage = JSON.parseObject(s, ShopMessage.class);
            ResultSet resultSet = shopService.deleteMessage(shopMessage);
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
