package com.yinjunbiao.pojo;

public class GoodsReply {
    private String userName;

    private String reply;

    private Long consultationId;

    @Override
    public String toString() {
        return "GoodsReply{" +
                "userName='" + userName + '\'' +
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

    public GoodsReply() {
    }

    public GoodsReply(String userName, String reply, Long consultationId) {
        this.userName = userName;
        this.reply = reply;
        this.consultationId = consultationId;
    }
}
