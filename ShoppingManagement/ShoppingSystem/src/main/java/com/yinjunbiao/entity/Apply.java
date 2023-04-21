package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 店铺申请
 */
public class Apply {
    private Integer id;

    private Integer userId;

    private Integer status;

    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", shopName='" + shopName + '\'' +
                '}';
    }

    public Apply(Integer id, Integer userId, Integer status, String shopName) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.shopName = shopName;
    }

    public Apply() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
