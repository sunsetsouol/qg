package com.yinjunbiao.MySpring;

public class BeanDefinition {
    private boolean isMapper;

    private Class clazz;

    private String Scope;

    public boolean isMapper() {
        return isMapper;
    }

    public void setMapper(boolean mapper) {
        isMapper = mapper;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return Scope;
    }

    public void setScope(String scope) {
        Scope = scope;
    }

    public BeanDefinition() {
    }

    public BeanDefinition(Class clazz, String scope) {
        this.clazz = clazz;
        Scope = scope;
    }
}
