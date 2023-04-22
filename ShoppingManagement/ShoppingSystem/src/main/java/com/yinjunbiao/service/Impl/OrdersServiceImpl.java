package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.Goods;
import com.yinjunbiao.entity.Orders;
import com.yinjunbiao.mapper.GoodsMapper;
import com.yinjunbiao.mapper.OrdersMapper;
import com.yinjunbiao.mapper.ShopMapper;
import com.yinjunbiao.mapper.UserMapper;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.ShopOrders;
import com.yinjunbiao.service.OrdersService;
import com.yinjunbiao.util.CONST;
import com.yinjunbiao.util.SqlSessionUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component("ordersService")
@Scope("singleton")
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private GoodsMapper goodsMapper;


    /**
     * 购买
     * @param orders
     * @return
     */
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
                    ordersMapper.insert(System.currentTimeMillis(),userMapper.selectById(shopMapper.selectById(orders.getShopId()).getBossId()).getAddress(),userMapper.selectById(orders.getUserId()).getAddress(),orders.getGoodsId(),orders.getShopId(),orders.getUserId(),orders.getNumber(),orders.getSinglePrice());
                    resultSet = ResultSet.success();
                }
            }
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }

    @Override
    public ResultSet selectOrdersByShopId(Integer shopId,Integer status,Integer currentPage,Integer pageSize) {
        List<Orders> orders = ordersMapper.selectByShopId(shopId,status,currentPage-1,pageSize);
        List<ShopOrders> shopOrders = new ArrayList<>();
        for (Orders order : orders) {
            Goods goods = goodsMapper.selectById(order.getGoodsId());
            ShopOrders shopOrder = new ShopOrders(order.getId(), CONST.dateFormat.format(order.getTime()),order.getSendAddress(),order.getReceiveAddress(),goods.getName(),order.getStatus(),userMapper.selectById(order.getUserId()).getUserName(),order.getNumber(),goods.getShopName(),goods.getPrice());
            shopOrders.add(shopOrder);
        }
        ResultSet resultSet = ResultSet.success(shopOrders,null);
        return resultSet;
    }

    @Override
    public ResultSet send(ShopOrders shopOrders) {
        int i = ordersMapper.updateStatus(2, shopOrders.getId());
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return i == 1 ? ResultSet.success() : ResultSet.error();
    }
}
