package com.yinjunbiao.web.Filter;


import com.yinjunbiao.util.JwtUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = {"/Manager.html","/manager/","/Myshop.html","/shop/"})
public class LoginFilter implements Filter {

    private static String []urls = {"/login.html","/register.html","forgetPassword.html","/css","/user/login","/user/register",".woff",".ttf","/json",".js",".css"};

    private static String []managers = {"/manager.html","/manager/"};

    private static String []shops = {"/MyShop.html","/shop/"};
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestUrl = request.getRequestURL().toString();
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String jwt = cookie.getValue();
                    Claims claims = JwtUtil.parseJWT(jwt);
                    Integer identify = (Integer) claims.get("identify");
                    for (String manager : managers) {
                        if (requestUrl.contains(manager)){
                            if (identify == 2){
                                chain.doFilter(req,resp);
                            }
                        }
                    }
                    for (String shop : shops) {
                        if (requestUrl.contains(shop)){
                            if (identify == 1){
                                chain.doFilter(req,resp);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            response.sendRedirect("/ShoppingSystem/login.html");
        }
        response.sendRedirect("/ShoppingSystem/login.html");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
