package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 商品咨询
 */
public class Consultation {

    private Long id;

    private Integer goodsId;

    private String consultation;

    private Integer userId;

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", consultation='" + consultation + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getConsultation() {
        return consultation;
    }

    public void setConsultation(String consultation) {
        this.consultation = consultation;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Consultation() {
    }

    public Consultation(Long id, Integer goodsId, String consultation, Integer userId) {
        this.id = id;
        this.goodsId = goodsId;
        this.consultation = consultation;
        this.userId = userId;
    }
}
