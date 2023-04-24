package com.yinjunbiao.pojo;

public class GoodsReply {
    private Long id;

    private String userName;

    private String reply;

    private Long consultationId;

    @Override
    public String toString() {
        return "GoodsReply{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", reply='" + reply + '\'' +
                ", consultationId=" + consultationId +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoodsReply() {
    }

    public GoodsReply(Long id, String userName, String reply, Long consultationId) {
        this.id = id;
        this.userName = userName;
        this.reply = reply;
        this.consultationId = consultationId;
    }
}
