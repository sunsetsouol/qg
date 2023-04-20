package com.yinjunbiao.service;

import com.yinjunbiao.entity.Goods;
import com.yinjunbiao.pojo.ResultSet;

import java.util.List;

public interface GoodsService {

    ResultSet selectByPage(Integer currentPage, Integer pageSize);

    ResultSet selectByName(String name,Integer currentPage, Integer pageSize);
}
