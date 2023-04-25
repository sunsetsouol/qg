package com.yinjunbiao.pojo;

public class ShopApply {
    private Integer id;

    private Integer userId;

    private String userName;

    private String shopName;

    private String description;

    @Override
    public String toString() {
        return "ShopApply{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", shopName='" + shopName + '\'' +
                ", description='" + description + '\'' +
                '}';
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShopApply() {
    }

    public ShopApply(Integer id, Integer userId, String userName, String shopName, String description) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.shopName = shopName;
        this.description = description;
    }
}
