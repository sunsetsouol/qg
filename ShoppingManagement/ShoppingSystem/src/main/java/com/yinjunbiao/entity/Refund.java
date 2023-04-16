package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 退货
 */
public class Refund {
    private Long id;

    private Long orderId;

    //退货原因
    private Integer cause;

    //退货状态
    private Integer status;

    private String description;

    @Override
    public String toString() {
        return "Refund{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", cause=" + cause +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getCause() {
        return cause;
    }

    public void setCause(Integer cause) {
        this.cause = cause;
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

    public Refund() {
    }

    public Refund(Long id, Long orderId, Integer cause, Integer status, String description) {
        this.id = id;
        this.orderId = orderId;
        this.cause = cause;
        this.status = status;
        this.description = description;
    }
}
