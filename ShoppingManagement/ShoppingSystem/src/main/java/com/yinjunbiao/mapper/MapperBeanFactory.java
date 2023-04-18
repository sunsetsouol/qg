package com.yinjunbiao.mapper;

import com.yinjunbiao.MySpring.BeanFactory;
import com.yinjunbiao.util.SqlSessionUtil;

public class MapperBeanFactory implements BeanFactory {

    private Class clazz;

    public MapperBeanFactory() {
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public MapperBeanFactory(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object getObject() {
        return SqlSessionUtil.getSqlSession().getMapper(clazz);
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }
}
