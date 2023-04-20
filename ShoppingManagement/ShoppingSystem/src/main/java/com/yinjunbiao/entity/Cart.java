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

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", number=" + number +
                ", userId=" + userId +
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

    public Cart() {
    }

    public Cart(Long id, Long goodsId, Integer number, Integer userId) {
        this.id = id;
        this.goodsId = goodsId;
        this.number = number;
        this.userId = userId;
    }
}
