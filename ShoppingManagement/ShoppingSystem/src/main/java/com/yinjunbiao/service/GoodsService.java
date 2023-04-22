package com.yinjunbiao.service;

import com.yinjunbiao.entity.Cart;
import com.yinjunbiao.entity.Goods;
import com.yinjunbiao.entity.Orders;
import com.yinjunbiao.pojo.ResultSet;

import java.util.List;

public interface GoodsService {

    ResultSet selectByPage(Integer currentPage, Integer pageSize);

    ResultSet selectByName(String name,Integer currentPage, Integer pageSize);

    ResultSet changeShoppingCart(Cart cart);
}
