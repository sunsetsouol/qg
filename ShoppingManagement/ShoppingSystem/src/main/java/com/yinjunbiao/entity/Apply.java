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

    private String description;

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

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", shopName='" + shopName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Apply(Integer id, Integer userId, Integer status, String shopName, String description) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.shopName = shopName;
        this.description = description;
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
