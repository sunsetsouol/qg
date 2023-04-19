package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 订单
 */
public class Orders {
    private Long id;

    private Long time;

    private String sendAddress;

    private String receiveAddress;

    private Long goodsId;

    private Integer userId;

    private Integer status;

    private String comment;

    private Integer number;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", time=" + time +
                ", sendAddress='" + sendAddress + '\'' +
                ", receiveAddress='" + receiveAddress + '\'' +
                ", goodsId=" + goodsId +
                ", userId=" + userId +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                ", number=" + number +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Orders() {
    }

    public Orders(Long id, Long time, String sendAddress, String receiveAddress, Long goodsId, Integer userId, Integer status, String comment, Integer number) {
        this.id = id;
        this.time = time;
        this.sendAddress = sendAddress;
        this.receiveAddress = receiveAddress;
        this.goodsId = goodsId;
        this.userId = userId;
        this.status = status;
        this.comment = comment;
        this.number = number;
    }
}
