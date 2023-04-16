package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 订阅
 */
public class Subscrible {
    private Long id;

    private Integer userId;

    private Integer shopId;

    @Override
    public String toString() {
        return "Subscrible{" +
                "id=" + id +
                ", userId=" + userId +
                ", shopId=" + shopId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Subscrible() {
    }

    public Subscrible(Long id, Integer userId, Integer shopId) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
    }
}
