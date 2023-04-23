package com.yinjunbiao.pojo;

import com.yinjunbiao.util.CONST;

public class RefundApply {

    private Long id;

    private Long ordersId;

    private String time;

    private String cause;

    private String description;

    private String userName;

    private String goodsName;

    @Override
    public String toString() {
        return "RefundApply{" +
                "id=" + id +
                ", ordersId=" + ordersId +
                ", time='" + time + '\'' +
                ", cause='" + cause + '\'' +
                ", description='" + description + '\'' +
                ", userName='" + userName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public RefundApply() {
    }

    public RefundApply(Long id, Long ordersId, String time, Integer cause, String description, String userName, String goodsName) {
        this.id = id;
        this.ordersId = ordersId;
        this.time = time;
        this.cause = CONST.CAUSE[cause];
        this.description = description;
        this.userName = userName;
        this.goodsName = goodsName;
    }
}
