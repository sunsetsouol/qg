package com.yinjunbiao.pojo;

public class ShoppingCart {

    private Long id;

    private String name;

    private Integer number;

    private String picture;

    private Integer price;

    private String shop;


    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", shop='" + shop + '\'' +
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public ShoppingCart() {
    }

    public ShoppingCart(Long id, String name, Integer number, String picture, Integer price, String shop) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.picture = picture;
        this.price = price;
        this.shop = shop;
    }
}
