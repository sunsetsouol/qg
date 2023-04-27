package com.yinjunbiao.pojo;

public class GoodsReport {
    private Integer id;

    private Long goodsId;

    private String goodsName;

    private String description;

    @Override
    public String toString() {
        return "GoodsRepoet{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GoodsReport() {
    }

    public GoodsReport(Integer id, Long goodsId, String goodsName, String description) {
        this.id = id;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.description = description;
    }
}
