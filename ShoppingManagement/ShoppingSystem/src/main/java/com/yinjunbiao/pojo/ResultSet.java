package com.yinjunbiao.pojo;

/**
 * 统一结果返回集
 * @author yinjunbiao
 */
public class ResultSet {
    private Integer code;
    private Object data;
    private String msg;

    public static ResultSet success(Object data, String msg){
        return new ResultSet(1,data,msg);
    }

    public static ResultSet success(){
        return new ResultSet(1,null,null);
    }
    public static ResultSet error(){
        return new ResultSet(0,null,null);
    }
    public static ResultSet error(Object data,String msg){
        return new ResultSet(0,data,msg);
    }
    @Override
    public String toString() {
        return "ResultSet{" +
                "code=" + code +
                ", data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultSet() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultSet(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
