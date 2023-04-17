package com.yinjunbiao.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析sql语句
 * @author yinjunbiao
 */
public class ParseSql {
    private List<String > params;

    public ParseSql() {
        params = new ArrayList<>();
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public  String parse(String sql) {
        int start = 0;
        int end = -1;
        while (start >= 0 && end < sql.length()){
            start = sql.indexOf("#",end+1);
            end = sql.indexOf("}",end+1);
            if (start < 0){
                break;
            }
            String param = sql.substring(start+2,end).trim();
            params.add(param);
        }
        return sql.replaceAll("#\\{[0-9a-zA-Z]*}","?");
    }
}
