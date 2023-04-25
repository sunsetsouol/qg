package com.yinjunbiao.service;

import com.yinjunbiao.entity.Apply;
import com.yinjunbiao.pojo.ResultSet;

public interface ManagerService {

    ResultSet selectShopApply();

    ResultSet agreeShopApply(Apply apply);

    ResultSet disagreeShopApply(Apply apply);
}
