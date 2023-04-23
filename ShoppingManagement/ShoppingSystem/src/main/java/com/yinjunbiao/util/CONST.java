package com.yinjunbiao.util;

import java.text.SimpleDateFormat;

/**
 * 常量类
 * @author yinjunbiao
 */
public class CONST {
    public static final String CONFIGXML = "mybatis-config.xml";

    public static final String UN_POOLED_DATASOURCE = "UNPOOLED";
    public static final String POOLED_DATASOURCE = "POOLED";
    public static final String JNDI_DATASOURCE = "JNDI";

    public static final String JDBC_TRANSACTION = "JDBC";
    public static final String MANAGED_TRANSACTION = "MANAGED";

    public static final String [] SENSITIVE = {"敏感词"};

    public static final String SELECT = "select";
    public static final String SET = "set";

    public static final String SINGLETON = "singleton";

    public static final String JWTKEY = "JwtKey";

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static final String[] STATUS = {"未发货","已发货", "已收货", "退款中","已退款","退款失败"};

    public static final String[] CAUSE = {"无理由","质量问题"};

}
