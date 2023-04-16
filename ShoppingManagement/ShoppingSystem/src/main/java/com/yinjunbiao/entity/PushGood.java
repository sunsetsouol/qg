package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 申请的商品
 */
public class PushGood {
    private Long id;

    private String goodsName;

    private Integer price;

    private Integer status;

    @Override
    public String toString() {
        return "PushGood{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
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

    public PushGood(Long id, String goodsName, Integer price, Integer status) {
        this.id = id;
        this.goodsName = goodsName;
        this.price = price;
        this.status = status;
    }
}
