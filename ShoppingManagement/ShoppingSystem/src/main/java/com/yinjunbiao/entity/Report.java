package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 举报商品
 */
public class Report {
    private Integer id;

    private Long goodsId;

    private Integer userId;

    private Integer status;

    private String description;

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", userId=" + userId +
                ", status=" + status +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Report() {
    }

    public Report(Integer id, Long goodsId, Integer userId, Integer status, String description) {
        this.id = id;
        this.goodsId = goodsId;
        this.userId = userId;
        this.status = status;
        this.description = description;
    }
}
