package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.Cart;
import com.yinjunbiao.entity.Goods;
import com.yinjunbiao.entity.Orders;
import com.yinjunbiao.entity.Refund;
import com.yinjunbiao.mapper.*;
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

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private RefundMapper refundMapper;


    /**
     * 购买
     *
     * @param orders
     * @return
     */
    @Override
    public ResultSet buy(Orders orders) {
        ResultSet resultSet = null;
        Goods goods = goodsMapper.selectById(orders.getGoodsId());
        if (orders.getNumber() > goods.getInventory()) {
            resultSet = ResultSet.error(null, "库存不足");
        } else {
            synchronized (ordersMapper) {
                goods = goodsMapper.selectById(orders.getGoodsId());
                if (orders.getNumber() > goods.getInventory()) {
                    resultSet = ResultSet.error(null, "库存不足");
                } else {
                    ordersMapper.insert(System.currentTimeMillis(), userMapper.selectById(shopMapper.selectById(orders.getShopId()).getBossId()).getAddress(), userMapper.selectById(orders.getUserId()).getAddress(), orders.getGoodsId(), orders.getShopId(), orders.getUserId(), orders.getNumber(), orders.getSinglePrice());
                    resultSet = ResultSet.success();
                }
            }
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }

    /**
     * 根据店铺id查询订单
     *
     * @param shopId
     * @param status
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public ResultSet selectOrdersByShopId(Integer shopId, Integer status, Integer currentPage, Integer pageSize) {
        List<Orders> orders = ordersMapper.selectByShopId(shopId, status, currentPage - 1, pageSize);
        List<ShopOrders> shopOrders = new ArrayList<>();
        for (Orders order : orders) {
            Goods goods = goodsMapper.selectById(order.getGoodsId());
            ShopOrders shopOrder = new ShopOrders(order.getId(), CONST.dateFormat.format(order.getTime()), order.getSendAddress(), order.getReceiveAddress(), goods.getName(), order.getStatus(), userMapper.selectById(order.getUserId()).getUserName(), order.getNumber(), goods.getShopName(), goods.getPrice());
            shopOrders.add(shopOrder);
        }
        SqlSessionUtil.close();
        ResultSet resultSet = ResultSet.success(shopOrders, null);
        return resultSet;
    }

    /**
     * 商品已发送，订单更新
     *
     * @param shopOrders
     * @return
     */
    @Override
    public ResultSet send(ShopOrders shopOrders) {
        int i = ordersMapper.updateStatus(2, shopOrders.getId());
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return i == 1 ? ResultSet.success() : ResultSet.error();
    }

    /**
     * 用户确定已收款
     *
     * @param id
     * @return
     */
    @Override
    public ResultSet confirm(Long id) {
        ResultSet resultSet = null;
        if (ordersMapper.updateStatus(3, id) == 1) {
            SqlSessionUtil.commit();
        } else {
            SqlSessionUtil.rollback();
        }
        SqlSessionUtil.close();
        return resultSet;
    }


    /**
     * 退款
     *
     * @param refund
     * @return
     */
    @Override
    public ResultSet refund(Refund refund) {
        //查找订单
        Orders select = ordersMapper.select(refund.getOrderId());
        ResultSet resultSet = null;
        if (select != null) {
            //订单存在且没有重复申请
            List<Refund> refunds = refundMapper.selectByOrderId(select.getId());
            //超过两次就不能继续申请了
            if (refunds == null || (refunds.size() < 2 && refunds.get(0).getStatus() != 0)) {
                synchronized (refundMapper) {
                    refunds = refundMapper.selectByOrderId(select.getId());
                    if (refunds == null || (refunds.size() < 2 && refunds.get(0).getStatus() != 0)) {
                        if (ordersMapper.updateStatus(4, refund.getOrderId()) == 1 && refundMapper.insert(refund.getOrderId(), refund.getCause(), refund.getDescription()) == 1) {
                            resultSet = ResultSet.success();
                            SqlSessionUtil.commit();
                        }
                    }
                }
            }
        }
        if (resultSet == null) {
            resultSet = ResultSet.error(null, "退款申请次数过多");
            SqlSessionUtil.rollback();
        }
        SqlSessionUtil.close();
        return resultSet;
    }
}
