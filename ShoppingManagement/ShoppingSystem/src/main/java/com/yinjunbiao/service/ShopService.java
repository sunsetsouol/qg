package com.yinjunbiao.service;

import com.yinjunbiao.entity.PushGood;
import com.yinjunbiao.pojo.RefundApply;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.ShopOrders;

public interface ShopService {
    ResultSet addPush(PushGood pushGood);

    ResultSet agree(RefundApply refundApply);

    ResultSet disagree(RefundApply refundApply);

    ResultSet selectRefund(Integer shopId);
}
