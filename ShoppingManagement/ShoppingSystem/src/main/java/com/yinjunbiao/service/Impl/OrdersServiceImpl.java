package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.entity.Goods;
import com.yinjunbiao.entity.Orders;
import com.yinjunbiao.mapper.GoodsMapper;
import com.yinjunbiao.mapper.OrdersMapper;
import com.yinjunbiao.mapper.ShopMapper;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.OrdersService;
import com.yinjunbiao.util.SqlSessionUtil;

public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public ResultSet buy(Orders orders) {
        ResultSet resultSet = null;
        Goods goods = goodsMapper.selectById(orders.getGoodsId());
        if (orders.getNumber() > goods.getInventory()){
            resultSet = ResultSet.error(null,"库存不足");
        }else {
            synchronized (ordersMapper){
                goods = goodsMapper.selectById(orders.getGoodsId());
                if (orders.getNumber() > goods.getInventory()){
                    resultSet = ResultSet.error(null,"库存不足");
                }else {
                    ordersMapper.insert(System.currentTimeMillis(),userMapper.selectById(shopMapper.selectById(goods.getShopId()).getBossId()).getAddress(),userMapper.selectById(orders.getUserId()).getAddress(),orders.getGoodsId(),orders.getUserId(),orders.getNumber(),orders.getSinglePrice());
                    resultSet = ResultSet.success();
                }
            }
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }
}
