package com.yinjunbiao.MyORM.MappedStatement;

import java.util.Map;

public class ResultMap {
    private String type;
    private Map<String,String> resultMaps;

    @Override
    public String toString() {
        return "ResultMap{" +
                "type='" + type + '\'' +
                ", resultMaps=" + resultMaps +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getResultMaps() {
        return resultMaps;
    }

    public void setResultMaps(Map<String, String> resultMaps) {
        this.resultMaps = resultMaps;
    }

    public ResultMap() {
    }

    public ResultMap(String type, Map<String, String> resultMaps) {
        this.type = type;
        this.resultMaps = resultMaps;
    }
}
