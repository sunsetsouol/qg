package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 推文（动态
 */
public class Tweets {
    private Integer id;

    private Integer shopId;

    private String tweet;

    @Override
    public String toString() {
        return "Tweets{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", tweet='" + tweet + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Tweets() {
    }

    public Tweets(Integer id, Integer shopId, String tweet) {
        this.id = id;
        this.shopId = shopId;
        this.tweet = tweet;
    }
}
