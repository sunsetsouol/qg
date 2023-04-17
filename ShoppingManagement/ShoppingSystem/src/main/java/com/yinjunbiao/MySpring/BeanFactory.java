package com.yinjunbiao.MySpring;

/**
 * bean工厂
 * @author yinjunbiao
 */
public interface BeanFactory {

    public Object getObject();

    public Class<?> getObjectType();
}
