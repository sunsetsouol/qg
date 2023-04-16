package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 举报商品
 */
public class Report {
    private Long id;

    private Long goodId;

    private Integer userId;

    private Integer status;

    private String description;

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", userId=" + userId +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public Report() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
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

    public Report(Long id, Long goodId, Integer userId, Integer status, String description) {
        this.id = id;
        this.goodId = goodId;
        this.userId = userId;
        this.status = status;
        this.description = description;
    }
}
