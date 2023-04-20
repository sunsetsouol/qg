package com.yinjunbiao.pojo;

public class ShoppingCart {

    private Long id;

    private Integer userId;

    private String goods;

    private String shopName;

    private Integer number;

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", userId=" + userId +
                ", goods='" + goods + '\'' +
                ", shopName='" + shopName + '\'' +
                ", number=" + number +
                '}';
    }

    public ShoppingCart() {
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

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ShoppingCart(Long id, Integer userId, String goods, String shopName, Integer number) {
        this.id = id;
        this.userId = userId;
        this.goods = goods;
        this.shopName = shopName;
        this.number = number;
    }
}
