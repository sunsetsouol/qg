package com.yinjunbiao.util;

import com.yinjunbiao.MySpring.ApplicationContext;
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
        ApplicationContext.registerFactory(new MapperBeanFactory(TweetsMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(OrdersMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(ShopMessageMapper.class));
        ApplicationContext.registerFactory(new MapperBeanFactory(UserMessageMapper.class));
        applicationContext = new ApplicationContext(UserServlet.class);
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
