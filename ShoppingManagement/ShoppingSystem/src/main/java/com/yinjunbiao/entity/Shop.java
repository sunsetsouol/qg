package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 店铺
 */
public class Shop {
    private Integer id;

    private Integer bossId;

    private Integer fans;

    private String introductation;

    private Integer sales;

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", bossId=" + bossId +
                ", fans=" + fans +
                ", introductation='" + introductation + '\'' +
                ", sales=" + sales +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBossId() {
        return bossId;
    }

    public void setBossId(Integer bossId) {
        this.bossId = bossId;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public String getIntroductation() {
        return introductation;
    }

    public void setIntroductation(String introductation) {
        this.introductation = introductation;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Shop() {
    }

    public Shop(Integer id, Integer bossId, Integer fans, String introductation, Integer sales) {
        this.id = id;
        this.bossId = bossId;
        this.fans = fans;
        this.introductation = introductation;
        this.sales = sales;
    }
}
