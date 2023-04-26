package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.*;
import com.yinjunbiao.mapper.*;
import com.yinjunbiao.pojo.RefundApply;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.ShopOrders;
import com.yinjunbiao.service.ShopService;
import com.yinjunbiao.util.CONST;
import com.yinjunbiao.util.SqlSessionUtil;

import java.util.ArrayList;
import java.util.List;

@Component("shopService")
@Scope("singleton")
public class ShopServiceImpl implements ShopService {

    @Autowired
    private PushGoodsMapper pushGoodsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private RefundMapper refundMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private TweetsMapper tweetsMapper;

    @Autowired
    private ShopMapper shopMapper;

    /**
     * 添加商品
     * @param pushGood
     * @return
     */
    @Override
    public ResultSet addPush(PushGood pushGood) {
        ResultSet resultSet = null;
        if (pushGoodsMapper.selectByShopIdAndName(pushGood.getShopId(), pushGood.getName()) == null) {
            synchronized (pushGoodsMapper){
                if (pushGoodsMapper.selectByShopIdAndName(pushGood.getShopId(), pushGood.getName()) == null) {
                    pushGoodsMapper.insert(pushGood.getName(),pushGood.getPrice(),pushGood.getDescription(),pushGood.getShopId(),pushGood.getInventory());
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

    /**
     * 查看退款申请
     * @param shopId
     * @return
     */
    @Override
    public ResultSet selectRefund(Integer shopId) {
        List<Refund> refunds = refundMapper.selectApplyingByShopId(shopId);
        List<RefundApply> refundApplies = new ArrayList<>();
        if (refundApplies == null){
            return ResultSet.error();
        }
        for (Refund refund : refunds) {
            Orders orders = ordersMapper.select(refund.getOrderId());
            RefundApply refundApply = new RefundApply(refund.getId(),refund.getOrderId(), CONST.dateFormat.format(refund.getTime()),refund.getCause(),refund.getDescription(),userMapper.selectById(orders.getUserId()).getUserName(),goodsMapper.selectById(orders.getGoodsId()).getName() );
            refundApplies.add(refundApply);
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(refundApplies,null);
    }

    /**
     * 同意退款
     * @param refundApply
     * @return
     */
    @Override
    public ResultSet agree(RefundApply refundApply) {
        ResultSet resultSet = null;
        Orders select = ordersMapper.select(refundApply.getOrdersId());
        if (select.getStatus() == 4){
            Refund refund = refundMapper.selectApplyingByOrderId(refundApply.getOrdersId());
            if (refund != null && refund.getStatus() == 0){
                refundMapper.updateStatus(1,refund.getId());
                ordersMapper.updateStatus(5,refund.getOrderId());
                SqlSessionUtil.commit();
                resultSet = ResultSet.success();
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
     * 不同意退款
     * @param refundApply
     * @return
     */
    @Override
    public ResultSet disagree(RefundApply refundApply) {
        ResultSet resultSet = null;
        Orders select = ordersMapper.select(refundApply.getOrdersId());
        if (select.getStatus() == 4){
            Refund refund = refundMapper.selectApplyingByOrderId(refundApply.getOrdersId());
            if (refund != null && refund.getStatus() == 0){
                refundMapper.updateStatus(2,refund.getId());
                ordersMapper.updateStatus(6,refund.getOrderId());
                SqlSessionUtil.commit();
                resultSet = ResultSet.success();
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
     * 发送推文
     * @param tweets
     * @return
     */
    @Override
    public ResultSet sendTweets(Tweets tweets) {
        tweetsMapper.insert(tweets.getShopId(),tweets.getTweets());
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success();
    }

    /**
     * 搜索店铺
     * @param shop
     * @return
     */
    @Override
    public ResultSet searchShop(Shop shop) {
        List<Shop> shops = shopMapper.selectIdByName("%" + shop.getName() + "%");
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(shops,null);
    }
}
