package com.yinjunbiao.pojo;

public class ShopTweets {
    private Long id;

    private Integer shopId;

    private String shopName;

    private String Tweets;

    @Override
    public String toString() {
        return "ShopTweets{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", Tweets='" + Tweets + '\'' +
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getTweets() {
        return Tweets;
    }

    public void setTweets(String tweets) {
        Tweets = tweets;
    }

    public ShopTweets() {
    }

    public ShopTweets(Long id, Integer shopId, String shopName, String tweets) {
        this.id = id;
        this.shopId = shopId;
        this.shopName = shopName;
        Tweets = tweets;
    }
}
