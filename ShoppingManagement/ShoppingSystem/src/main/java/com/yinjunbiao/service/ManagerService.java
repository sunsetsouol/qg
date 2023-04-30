package com.yinjunbiao.service;

import com.yinjunbiao.entity.Apply;
import com.yinjunbiao.entity.PushGood;
import com.yinjunbiao.entity.Report;
import com.yinjunbiao.pojo.GoodsConsultations;
import com.yinjunbiao.pojo.GoodsReply;
import com.yinjunbiao.pojo.ResultSet;

public interface ManagerService {

    ResultSet deleteGoods(Long id);

    ResultSet deleteReply(GoodsReply goodsReply);

    ResultSet deleteConsultations(GoodsConsultations goodsConsultations);

    ResultSet selectShopApply();

    ResultSet agreeShopApply(Apply apply);

    ResultSet disagreeShopApply(Apply apply);

    ResultSet selectPushApply();

    ResultSet disagreePushGoods(PushGood pushGood);

    ResultSet agreePushGoods(PushGood pushGood);

    ResultSet selectReport();

    ResultSet disagreeReport(Report report);
}
