package com.yinjunbiao.web;

import com.yinjunbiao.util.SqlSessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        String requestURI = req.getRequestURI();
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        Class<? extends BaseServlet> aClass = this.getClass();

        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            SqlSessionUtil.rollback();
            SqlSessionUtil.close();
            e.printStackTrace();
        }
    }
}
