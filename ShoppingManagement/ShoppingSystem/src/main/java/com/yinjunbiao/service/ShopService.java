package com.yinjunbiao.service;

import com.yinjunbiao.entity.PushGood;
import com.yinjunbiao.entity.Shop;
import com.yinjunbiao.entity.Tweets;
import com.yinjunbiao.pojo.RefundApply;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.ShopOrders;
import com.yinjunbiao.pojo.ShopTweets;

import java.io.InputStream;

public interface ShopService {
    ResultSet addPush(PushGood pushGood);

    ResultSet agree(RefundApply refundApply);

    ResultSet disagree(RefundApply refundApply);

    ResultSet selectRefund(Integer shopId);

    ResultSet sendTweets(Tweets tweets);

    ResultSet searchShop(Shop shop);


    ResultSet searchShopGoods(Integer id, Integer currentPage, Integer pageSize);

    ResultSet searchPushingGoods(Integer shopId);

    ResultSet changePicture(InputStream inputStream, Long pushId);

    ResultSet selectTweets(Integer shopId);

    ResultSet deleteTweets(ShopTweets shopTweets);
}
