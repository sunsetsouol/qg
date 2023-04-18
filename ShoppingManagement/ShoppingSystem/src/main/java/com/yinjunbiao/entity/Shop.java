package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 店铺
 */
public class Shop {
    private Integer id;

    private Integer bossId;

    private Integer fans;

    private String description;

    private Integer sales;

    private String name;

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", bossId=" + bossId +
                ", fans=" + fans +
                ", description='" + description + '\'' +
                ", sales=" + sales +
                ", name='" + name + '\'' +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop() {
    }

    public Shop(Integer id, Integer bossId, Integer fans, String description, Integer sales, String name) {
        this.id = id;
        this.bossId = bossId;
        this.fans = fans;
        this.description = description;
        this.sales = sales;
        this.name = name;
    }
}
