package com.yinjunbiao.pojo;

public class UserSubscrible {
    private Long id;

    private Integer shopId;

    private String shopName;

    @Override
    public String toString() {
        return "UserSubscrible{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserSubscrible(Long id, Integer shopId, String shopName) {
        this.id = id;
        this.shopId = shopId;
        this.shopName = shopName;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public UserSubscrible() {
    }


}
