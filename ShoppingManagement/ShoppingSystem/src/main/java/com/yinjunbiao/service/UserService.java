package com.yinjunbiao.service;


import com.yinjunbiao.entity.*;
import com.yinjunbiao.pojo.ResultSet;

public interface UserService {

    ResultSet login(User user);

    ResultSet register(User user);

    ResultSet changePassword(User user);

    ResultSet changePhone(User user);

    ResultSet changeHeadshot(String headshot,Integer id);

    ResultSet changeMessage(User user);

    ResultSet applyShop(Apply apply);

    ResultSet selectMyShoppingCart(Integer id);

    ResultSet delectShoppingCart(Long id);

    ResultSet changeShoppingCart(Cart cart);

    ResultSet buyAllcart(Integer id);

    ResultSet buyCart(Cart cart);

    ResultSet buy(Orders orders);

    ResultSet refund(Refund refund);

    ResultSet subscrible(Subscrible subscrible);

    ResultSet selectSub(Subscrible subscrible);

    ResultSet selectMySubs(Integer userId);

    ResultSet sendConsultation(Consultation consultation);

    ResultSet deleteConsultation(Long id);

    ResultSet sendReply(Reply reply);

    ResultSet deleteReply(Long id);

    ResultSet reportGoods(Report report);

    ResultSet selectMyShop(Integer id);

    ResultSet addCart(Cart cart);

    ResultSet newOrders(Orders orders);
}
