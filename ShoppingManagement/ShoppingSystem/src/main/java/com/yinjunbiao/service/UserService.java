package com.yinjunbiao.service;


import com.yinjunbiao.entity.*;
import com.yinjunbiao.pojo.ResultSet;

public interface UserService {

    ResultSet login(User user);

    ResultSet register(User user);

    ResultSet changePassword(User user);

    ResultSet changePhone(User user);

    ResultSet changeAddress(String address,Integer id);

    ResultSet changeHeadshot(String headshot,Integer id);

    ResultSet changePrivate(Integer isPrivate,Integer id);

    ResultSet changeUserName(String userName,Integer id);

    ResultSet applyShop(Integer id);

    ResultSet selectMyShoppingCart(Integer id);

    ResultSet addShoppingCart(ShoppingCart cart);

    ResultSet delectShoppingCart(Long id);

    ResultSet changeShoppingCart(ShoppingCart cart);

    ResultSet buyAllcart(Integer id);

    ResultSet buyCart(ShoppingCart cart);

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
}
