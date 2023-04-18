package com.yinjunbiao.web.Filter;


import com.alibaba.fastjson.JSON;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.util.JwtUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String []urls = {"/login.html","/register.html","forgetPassword.html","/css","/user/login","/user/register",".woff",".ttf","/json",".js",".css"};
        String requestUrl = request.getRequestURL().toString();
        for (String url : urls) {
            if (requestUrl.contains(url)){
                chain.doFilter(req, resp);
                return;
            }
        }

        String token = request.getHeader("Authorization");
        if (token == null || token.length() == 0){
            HttpServletResponse response = (HttpServletResponse) resp;

            response.sendRedirect("/ShoppingSystem/login.html");
            return;
        }
        Claims claims = JwtUtil.parseJWT(token);
        if(claims == null){
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("/ShoppingSystem/login.html");

        }else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
