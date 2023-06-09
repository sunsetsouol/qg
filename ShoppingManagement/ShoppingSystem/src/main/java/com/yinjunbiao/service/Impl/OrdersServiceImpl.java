package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.Goods;
import com.yinjunbiao.entity.Orders;
import com.yinjunbiao.entity.Refund;
import com.yinjunbiao.mapper.*;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.ShopOrders;
import com.yinjunbiao.service.OrdersService;
import com.yinjunbiao.util.CONST;
import com.yinjunbiao.util.SqlSessionUtil;

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
     * 生成订单(购买
     *
     * @param orders
     * @return
     */
    @Override
    public ResultSet newOrders(Orders orders) {
        ResultSet resultSet = null;
        Goods goods = goodsMapper.selectById(orders.getGoodsId());
        if (shopMapper.selectById(goods.getShopId()).getBossId().equals(orders.getUserId())) {
            return ResultSet.error(null, "不能购买自己的商品");
        }
        if (orders.getNumber() == 0){
            return ResultSet.error(null,"不能买0件");
        }
        if (orders.getNumber() <= goods.getInventory()) {
            synchronized (goodsMapper) {
                goods = goodsMapper.selectById(orders.getGoodsId());
                if (orders.getNumber() <= goods.getInventory()) {
                    //插入订单同时库存减
                    ordersMapper.insert(System.currentTimeMillis(), userMapper.selectById(shopMapper.selectById(goods.getShopId()).getBossId()).getAddress(), userMapper.selectById(orders.getUserId()).getAddress(), orders.getGoodsId(), orders.getShopId(), orders.getUserId(), orders.getNumber(), orders.getSinglePrice());
                    shopMapper.updateSales(shopMapper.selectById(orders.getShopId()).getSales()+orders.getNumber(),orders.getShopId());
                    goodsMapper.updateInventory(goods.getInventory() - orders.getNumber(), orders.getGoodsId());
                    resultSet = ResultSet.success();
                }
            }
        }
        if (resultSet == null) {
            SqlSessionUtil.rollback();
            resultSet = ResultSet.error(null, "库存不足");
        } else {
            SqlSessionUtil.commit();
        }
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
        List<Orders> orders = ordersMapper.selectByShopId(shopId, status, (currentPage-1)*pageSize, pageSize);
        List<ShopOrders> shopOrders = new ArrayList<>();
        if (orders != null){
            for (Orders order : orders) {
                Goods goods = goodsMapper.selectById(order.getGoodsId());
                ShopOrders shopOrder = new ShopOrders(order.getId(), CONST.dateFormat.format(order.getTime()), order.getSendAddress(), order.getReceiveAddress(), goods.getName(), order.getStatus(), userMapper.selectById(order.getUserId()).getUserName(), order.getNumber(), goods.getShopName(), goods.getPrice());
                shopOrders.add(shopOrder);
            }
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
        Orders orders = ordersMapper.select(id);
        if (orders.getStatus() == 2){
            synchronized (shopMapper){
                if (ordersMapper.select(id).getStatus() == 2){
                    ordersMapper.updateStatus(3,id);
                    synchronized (goodsMapper){
                        Integer sales = goodsMapper.selectById(orders.getGoodsId()).getSales();
                        goodsMapper.updateSales(sales+orders.getNumber(),orders.getGoodsId());
                        SqlSessionUtil.commit();
                        resultSet = ResultSet.success();
                    }
                }
            }
        }
        if (resultSet == null){
            resultSet = ResultSet.error();
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
        if(refund.getDescription() == null || refund.getDescription().length() == 0){
            refund.setDescription("无");
        }
        //查找订单
        Orders select = ordersMapper.select(refund.getOrderId());
        ResultSet resultSet = null;
        if (select != null && select.getStatus() != 5 && select.getStatus() != 4) {
            //订单存在且没有重复申请
            Refund refund1 = refundMapper.selectApplyingByOrderId(select.getId());
            //不能继续申请了
            if (refund1 == null) {
                synchronized (refundMapper) {
                    select = ordersMapper.select(refund.getOrderId());
                    refund1 = refundMapper.selectApplyingByOrderId(select.getId());
                    if (refund1 == null && select.getStatus() != 5 && select.getStatus() != 4) {
                        if (ordersMapper.updateStatus(4, refund.getOrderId()) == 1 && refundMapper.insert(refund.getOrderId(), refund.getCause(), refund.getDescription(),System.currentTimeMillis(),select.getShopId()) == 1) {
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
