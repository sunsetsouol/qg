package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 店铺申请
 */
public class Apply {
    private Integer id;

    private Integer userId;

    private Integer status;

    @Override
    public String toString() {
        return "ToShop{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                '}';
    }

    public Apply() {
    }

    public Apply(Integer id, Integer userId, Integer status) {
        this.id = id;
        this.userId = userId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
