package com.yinjunbiao.entity;

public class ShopMessage {
    private Integer id;

    private Integer shopId;

    private String message;

    @Override
    public String toString() {
        return "ShopMessage{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", message='" + message + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ShopMessage() {
    }

    public ShopMessage(Integer id, Integer shopId, String message) {
        this.id = id;
        this.shopId = shopId;
        this.message = message;
    }
}
