package com.yinjunbiao.service;


import com.yinjunbiao.entity.*;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.UserSubscrible;

import java.io.InputStream;

public interface UserService {

    ResultSet login(User user);

    ResultSet register(User user);

    ResultSet changePassword(User user);

    ResultSet changePhone(User user);

    ResultSet changeHeadshot(InputStream headshot, Integer id);

    ResultSet applyShop(Apply apply);

    ResultSet selectMyShoppingCart(Integer id);

    ResultSet changeShoppingCart(Cart cart);

    ResultSet selectMyOrders(Integer userId,Integer status);

    ResultSet selectSub(Subscrible subscrible);

    ResultSet sendConsultation(Consultation consultation);

    ResultSet deleteConsultation(Long id);

    ResultSet sendReply(Reply reply);

    ResultSet deleteReply(Long id);

    ResultSet selectMyShop(Integer id);

    ResultSet addCart(Cart cart);

    ResultSet newOrders(Orders orders);

    ResultSet delectCarts(Long[] id);

    ResultSet buyCarts(Long[] ids);

    ResultSet selectUser(User user);

    ResultSet subscrible(Subscrible subscrible);

    ResultSet getHeadshot(Integer id);

    ResultSet reportGoods(Report report);

    ResultSet selectTweets(Integer id);

    ResultSet selectSubscrible(Integer id);

    ResultSet unfollow(UserSubscrible userSubscrible, Integer id);

    ResultSet selectPersonal(Integer id);

    ResultSet updatePersonal(User user);
}
