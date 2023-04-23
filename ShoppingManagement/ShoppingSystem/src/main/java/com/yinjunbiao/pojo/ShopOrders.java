package com.yinjunbiao.pojo;

import com.yinjunbiao.util.CONST;

import java.time.LocalDateTime;

public class ShopOrders {
    private Long id;

    private String time;

    private String sendAddress ;

    private String receiveAddress;

    private String goodsName;

    private String  status;

    private String userName;

    private Integer number;

    private String shopName;

    private Integer singlePrice;

    private Integer price;

    @Override
    public String toString() {
        return "ShopOrders{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", sendAddress='" + sendAddress + '\'' +
                ", receiveAddress='" + receiveAddress + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", status='" + status + '\'' +
                ", userName='" + userName + '\'' +
                ", number=" + number +
                ", shopName='" + shopName + '\'' +
                ", singlePrice=" + singlePrice +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public Integer getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Integer singlePrice) {
        this.singlePrice = singlePrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ShopOrders() {
    }

    public ShopOrders(Long id, String time, String sendAddress, String receiveAddress, String goodsName, Integer status, String userName, Integer number, String shopName, Integer singlePrice) {
        this.id = id;
        this.time = time;
        this.sendAddress = sendAddress;
        this.receiveAddress = receiveAddress;
        this.goodsName = goodsName;
        this.status = CONST.STATUS[status-1];
        this.userName = userName;
        this.number = number;
        this.shopName = shopName;
        this.singlePrice = singlePrice;
        this.price = singlePrice * number;
    }
}
