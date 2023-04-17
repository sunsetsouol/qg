package com.yinjunbiao.MySpring;

/**
 * 初始化前后函数
 * @author yinjunbiao
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean,String beanName);

    Object postProcessAfterInitialization(Object bean,String beanName);
}
