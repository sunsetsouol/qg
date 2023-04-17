package com.yinjunbiao.util;

import com.yinjunbiao.MySpring.ApplicationContext;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.util.MapperBeanFactory;
import com.yinjunbiao.web.UserServlet;

public class ApplicationUtil {
    private static ApplicationContext applicationContext;
    static {
        ApplicationContext.registerFactory(new MapperBeanFactory(UserMapper.class));
        applicationContext = new ApplicationContext(UserServlet.class);
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
