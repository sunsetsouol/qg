package com.yinjunbiao.entity;

import com.mysql.cj.jdbc.Blob;

/**
 * @author yinjunbiao
 * 申请的商品
 */
public class PushGood {
    private Long id;

    private String name;

    private Integer price;

    private Integer status;

    private String  picture;

    @Override
    public String toString() {
        return "PushGood{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", picture='" + picture + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public PushGood() {
    }

    public PushGood(Long id, String name, Integer price, Integer status, String picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.picture = picture;
    }
}
