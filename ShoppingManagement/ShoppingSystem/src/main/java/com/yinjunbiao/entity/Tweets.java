package com.yinjunbiao.entity;

/**
 * @author yinjunbiao
 * 推文（动态
 */
public class Tweets {
    private Long id;

    private Integer shopId;

    private String tweets;

    @Override
    public String toString() {
        return "Tweets{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", tweets='" + tweets + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getTweets() {
        return tweets;
    }

    public void setTweets(String tweets) {
        this.tweets = tweets;
    }

    public Tweets() {
    }

    public Tweets(Long id, Integer shopId, String tweets) {
        this.id = id;
        this.shopId = shopId;
        this.tweets = tweets;
    }
}
