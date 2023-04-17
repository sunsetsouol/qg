package com.yinjunbiao.entity;

import com.mysql.cj.jdbc.Blob;

/**
 * @author yinjunbiao
 * 申请的商品
 */
public class PushGood {
    private Long id;

    private String goodsName;

    private Integer price;

    private Integer status;

    private Blob picture;

    @Override
    public String toString() {
        return "PushGood{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", picture=" + picture +
                '}';
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public PushGood() {
    }

    public PushGood(Long id, String goodsName, Integer price, Integer status, Blob picture) {
        this.id = id;
        this.goodsName = goodsName;
        this.price = price;
        this.status = status;
        this.picture = picture;
    }
}
