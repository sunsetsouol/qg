package com.yinjunbiao.pojo;

public class GoodsConsultations {
    private Long id;

    private String userName;

    private String consultation;

    @Override
    public String toString() {
        return "GoodsConsultations{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", consultation='" + consultation + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getConsultation() {
        return consultation;
    }

    public void setConsultation(String consultation) {
        this.consultation = consultation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoodsConsultations() {
    }

    public GoodsConsultations(Long id, String userName, String consultation) {
        this.id = id;
        this.userName = userName;
        this.consultation = consultation;
    }
}
