package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.PushGood;
import com.yinjunbiao.mapper.PushGoodsMapper;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.ShopService;
import com.yinjunbiao.util.SqlSessionUtil;

@Component("shopService")
@Scope("singleton")
public class ShopServiceImpl implements ShopService {

    @Autowired
    private PushGoodsMapper pushGoodsMapper;

    @Override
    public ResultSet addPush(PushGood pushGood) {
        ResultSet resultSet = null;
        if (pushGoodsMapper.selectByShopIdAndName(pushGood.getShopId(), pushGood.getName()) == null) {
            synchronized (pushGoodsMapper){
                if (pushGoodsMapper.selectByShopIdAndName(pushGood.getShopId(), pushGood.getName()) == null) {
                    pushGoodsMapper.insert(pushGood.getName(),pushGood.getPrice(),pushGood.getDescription(),pushGood.getShopId());
                    resultSet= ResultSet.success();
                }
            }
        }
        if (resultSet == null){
            resultSet = ResultSet.error();
            SqlSessionUtil.rollback();
        }else {
            SqlSessionUtil.commit();
        }
        SqlSessionUtil.close();
        return resultSet;
    }
}
