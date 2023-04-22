package com.yinjunbiao.service;

import com.yinjunbiao.entity.Orders;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.ShopOrders;

public interface OrdersService {
    ResultSet buy(Orders orders);

    ResultSet selectOrdersByShopId(Integer shopId,Integer status,Integer currentPage,Integer pageSize);

    ResultSet send(ShopOrders shopOrders);
}
