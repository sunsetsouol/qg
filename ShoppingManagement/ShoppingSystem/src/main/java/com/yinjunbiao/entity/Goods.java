package com.yinjunbiao.entity;

import com.mysql.cj.jdbc.Blob;

/**
 * @author yinjunbiao
 * 货物
 */
public class Goods {
    private Long id;

    private Integer shopId;

    private String description;

    private Integer sales;

    //库存
    private Integer inventory;

    private Integer price;

    private Blob picture;

    private String name;

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", description='" + description + '\'' +
                ", sales=" + sales +
                ", inventory=" + inventory +
                ", price=" + price +
                ", picture=" + picture +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public Goods() {
    }


}
