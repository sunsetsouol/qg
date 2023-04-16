package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 商品咨询的回复
 */
public class Reply {
    private Long id;

    private Long consultationId;

    private String reply;

    private Integer userId;

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", consultationId=" + consultationId +
                ", reply='" + reply + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Reply() {
    }

    public Reply(Long id, Long consultationId, String reply, Integer userId) {
        this.id = id;
        this.consultationId = consultationId;
        this.reply = reply;
        this.userId = userId;
    }
}
