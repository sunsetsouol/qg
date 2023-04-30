package com.yinjunbiao.web;

import com.alibaba.fastjson.JSON;
import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.ComponentScan;
import com.yinjunbiao.MySpring.Annotation.Scope;

import com.yinjunbiao.entity.*;
import com.yinjunbiao.pojo.CheckCodeUser;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.UserSubscrible;
import com.yinjunbiao.service.OrdersService;
import com.yinjunbiao.service.UserService;
import com.yinjunbiao.util.*;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
    private OrdersService ordersService = ((OrdersService) ApplicationUtil.getApplicationContext().getBean("ordersService"));


    /**
     * 登录
     * @param request
     * @param response
     * @throws IOException
     */
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
        LOGGER.info(user.toString());
        //查找并判断密码是否正确
        ResultSet resultSet = userService.login(user);

        if (resultSet.getCode() == 1) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", ((User) resultSet.getData()).getId());
            claims.put("identify",((User) resultSet.getData()).getIdentify());

            String token = JwtUtil.generateJwt(claims);
            Cookie c_token = new Cookie("token",token);
            c_token.setMaxAge(60*60*24*7);
            c_token.setPath("/");
            response.addCookie(c_token);
            resultSet.setData(token);
        }
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws IOException
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")){
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        return;
    }


    /**
     * 获取头像
     * @param request
     * @param response
     * @throws IOException
     */
    public void getHeadshot(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String token = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(token);
            Integer id = (Integer) claims.get("id");
            ResultSet resultSet = userService.getHeadshot(id);
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        }catch (Exception e){
            e.printStackTrace();
            SqlSessionUtil.close();
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }
    /**
     * 改头像
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void changeHeadshot(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Part imageUrl = request.getPart("file");
        InputStream inputStream = imageUrl.getInputStream();
        try(inputStream){
            Integer id = null;
            ResultSet resultSet = null;
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String value = cookie.getValue();
                    Claims claims = JwtUtil.parseJWT(value);
                    id = (Integer) claims.get("id");
                }
            }
            if (id != null){
                resultSet = userService.changeHeadshot(inputStream, id);
            }else {
                resultSet = ResultSet.error();
            }
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        }catch (Exception e){
            e.printStackTrace();
            SqlSessionUtil.close();
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }



    /**
     * 注册
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //读取json数据转换成user，并判断是否为空
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        CheckCodeUser checkCodeUser = JSON.parseObject(s, CheckCodeUser.class);
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        if (!checkCodeUser.getCheckCode().equalsIgnoreCase(checkCode)){
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResultSet.error("checkcode","验证码错误")));
            return;
        }
        User user = new User(null,null,checkCodeUser.getPhone(),null,checkCodeUser.getUserName(),checkCodeUser.getAddress(),checkCodeUser.getPassword(),checkCodeUser.getIsPrivate(),null);
        ResultSet resultSet = userService.register(user);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));

    }

    /**
     * 忘记密码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void forget(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //读取json数据转换成user，并判断是否为空
        BufferedReader reader = request.getReader();
        String s = reader.readLine();

        CheckCodeUser checkCodeUser = JSON.parseObject(s, CheckCodeUser.class);
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        if (!checkCodeUser.getCheckCode().equalsIgnoreCase(checkCode)){
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResultSet.error("checkcode","验证码错误")));
            return;
        }
        User user = new User(null,null,checkCodeUser.getPhone(),null,null,null,checkCodeUser.getPassword(),null,null);
        ResultSet resultSet = userService.changePassword(user);
        response.setStatus(200);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(resultSet));
    }



    /**
     * 添加到购物车
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void addCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        try {
            Claims claims = JwtUtil.parseJWT(token);
            Integer id = (Integer) claims.get("id");
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Cart cart = JSON.parseObject(s, Cart.class);
            cart.setUserId(id);
            ResultSet resultSet = userService.addCart(cart);
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
     * 申请称为商家
     *
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
        } catch (Exception e) {
            e.printStackTrace();
            SqlSessionUtil.close();
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 查询购物车
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void searchMyCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
            Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");
            ResultSet resultSet = userService.selectMyShoppingCart(id,currentPage,pageSize);
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
     * 查询自己的店铺
     *
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
        } catch (Exception e) {
            e.printStackTrace();
            SqlSessionUtil.close();
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 查看自己的订单
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void myOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Integer status = Integer.valueOf(request.getParameter("status"));
            Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
            Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");
            ResultSet resultSet = userService.selectMyOrders(id,status,currentPage,pageSize);
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
     * 批量删除购物车
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void deteleCartByIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Long[] ids = JSON.parseObject(s, Long[].class);
            ResultSet resultSet = userService.delectCarts(ids);
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
     * 批量购买购物车
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void buyCartByIds(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Long[] ids = JSON.parseObject(s, Long[].class);
            ResultSet resultSet = userService.buyCarts(ids);
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
     * 搜搜用户
     * @param request
     * @param response
     * @throws IOException
     */
    public void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            User user = JSON.parseObject(s, User.class);
            ResultSet resultSet = userService.selectUser(user);
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
     * 订阅店铺
     * @param request
     * @param response
     * @throws IOException
     */
    public void subscrible(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Shop shop = JSON.parseObject(s, Shop.class);
            Subscrible subscrible = new Subscrible();
            subscrible.setShopId(shop.getId());
            subscrible.setUserId(((Integer) claims.get("id")));
            ResultSet resultSet = userService.subscrible(subscrible);
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
     * 举报商品
     * @param request
     * @param response
     * @throws IOException
     */
    public void reportGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            Report report = JSON.parseObject(s, Report.class);
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");
            report.setUserId(id);
            ResultSet resultSet = userService.reportGoods(report);
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
     * 查找关注店铺的推文
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectTweets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");
            ResultSet resultSet = userService.selectTweets(id);
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
     * 查找关注店铺
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectSubscrible(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");
            ResultSet resultSet = userService.selectSubscrible(id);
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
     * 取关
     * @param request
     * @param response
     * @throws IOException
     */
    public void unfollow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            UserSubscrible userSubscrible = JSON.parseObject(s, UserSubscrible.class);
            ResultSet resultSet = userService.unfollow(userSubscrible,id);
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
     * 查看个人信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void personal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");
            ResultSet resultSet = userService.selectPersonal(id);
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
     * 修改个人信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void updatePersonal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            User user = JSON.parseObject(s, User.class);
            ResultSet resultSet = userService.updatePersonal(user);
            response.setStatus(200);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(resultSet));
        } catch (Exception e) {
            SqlSessionUtil.close();
            e.printStackTrace();
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 修改个人信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            HttpSession session = request.getSession();
            String checkCode = CheckCodeUtil.outputVerifyImage(100,50,outputStream,4);
            session.setAttribute("checkCode",checkCode);
        } catch (Exception e) {
            SqlSessionUtil.close();
            e.printStackTrace();
            response.sendRedirect("/ShoppingSystem/login.html");
        }
    }

    /**
     * 查看用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectMessages(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(authorization);
            Integer id = (Integer) claims.get("id");
            ResultSet resultSet = userService.selectMessage(id);
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
     * 删除用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            BufferedReader reader = request.getReader();
            String s = reader.readLine();
            UserMessage userMessage = JSON.parseObject(s, UserMessage.class);
            ResultSet resultSet = userService.deleteMessage(userMessage);
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
