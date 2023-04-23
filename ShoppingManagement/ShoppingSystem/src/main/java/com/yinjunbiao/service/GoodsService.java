package com.yinjunbiao.service;

import com.yinjunbiao.entity.*;
import com.yinjunbiao.pojo.ResultSet;

import java.util.List;

public interface GoodsService {

    ResultSet selectByPage(Integer currentPage, Integer pageSize);

    ResultSet selectByName(String name,Integer currentPage, Integer pageSize);

    ResultSet changeShoppingCart(Cart cart);

    ResultSet selectConsultation(Long id);

    ResultSet sendConsultation(Consultation consultation);

    ResultSet sendReply(Reply reply);

    ResultSet selectReply(Long id);
}
