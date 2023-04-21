package com.yinjunbiao.service;

import com.yinjunbiao.entity.Orders;
import com.yinjunbiao.pojo.ResultSet;

public interface OrdersService {
    ResultSet buy(Orders orders);
}
