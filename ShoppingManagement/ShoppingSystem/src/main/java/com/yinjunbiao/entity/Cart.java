package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 购物车
 */
public class Cart {
    private Long id;

    private Long goodsId;

    private Integer number;

    private Integer userId;

    private Integer singlePrice;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", number=" + number +
                ", userId=" + userId +
                ", singlePrice=" + singlePrice +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Integer singlePrice) {
        this.singlePrice = singlePrice;
    }

    public Cart() {
    }

    public Cart(Long id, Long goodsId, Integer number, Integer userId, Integer singlePrice) {
        this.id = id;
        this.goodsId = goodsId;
        this.number = number;
        this.userId = userId;
        this.singlePrice = singlePrice;
    }
}
