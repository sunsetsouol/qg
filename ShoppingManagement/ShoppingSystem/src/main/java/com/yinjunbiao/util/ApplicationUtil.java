package com.yinjunbiao.util;

import com.yinjunbiao.MySpring.ApplicationContext;
import com.yinjunbiao.entity.Apply;
import com.yinjunbiao.mapper.*;
import com.yinjunbiao.web.UserServlet;

public class ApplicationUtil {
    private static ApplicationContext applicationContext;
    static {
        ApplicationContext.registerFactory(new MapperBeanFactory(UserMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(GoodsMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(ApplyMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(CartMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(ConsultationMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(PushGoodsMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(RefundMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(ReplyMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(ReportMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(ShopMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(SubscribleMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(TweetMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(OrderMapper.class));
        applicationContext = new ApplicationContext(UserServlet.class);
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
