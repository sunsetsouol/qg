package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.*;
import com.yinjunbiao.mapper.*;
import com.yinjunbiao.pojo.RefundApply;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.ShopTweets;
import com.yinjunbiao.service.ShopService;
import com.yinjunbiao.util.CONST;
import com.yinjunbiao.util.SqlSessionUtil;
import com.yinjunbiao.util.UploadUtil;

import java.io.InputStream;
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

    @Autowired
    private ShopMessageMapper shopMessageMapper;

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
    public ResultSet selectRefund(Integer shopId,Integer currentPage, Integer pageSize) {
        List<Refund> refunds = refundMapper.selectApplyingByShopId(shopId,(currentPage-1)*pageSize,pageSize);
        List<RefundApply> refundApplies = new ArrayList<>();
        if (refunds != null){
            for (Refund refund : refunds) {
                Orders orders = ordersMapper.select(refund.getOrderId());
                if (orders != null){
                    RefundApply refundApply = new RefundApply(refund.getId(),refund.getOrderId(), CONST.dateFormat.format(refund.getTime()),refund.getCause(),refund.getDescription(),userMapper.selectById(orders.getUserId()).getUserName(),goodsMapper.selectById(orders.getGoodsId()).getName() );
                    refundApplies.add(refundApply);
                }
            }
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

    /**
     * 查找店铺的商品
     * @param id
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public ResultSet searchShopGoods(Integer id, Integer currentPage, Integer pageSize) {
        List<Goods> goods = goodsMapper.selectByShopId(id, (currentPage-1)*pageSize, pageSize);
        SqlSessionUtil.close();
        return ResultSet.success(goods,null);
    }

    /**
     * 查找店铺申请中的货物
     * @param shopId
     * @return
     */
    @Override
    public ResultSet searchPushingGoods(Integer shopId) {
        List<PushGood> pushGoods = pushGoodsMapper.selectByShopId(shopId);
        SqlSessionUtil.close();
        return ResultSet.success(pushGoods,null);
    }

    /**
     * 添加货物图片
     * @param inputStream
     * @param pushId
     * @return
     */
    @Override
    public ResultSet changePicture(InputStream inputStream, Long pushId) {
        String headshot = UploadUtil.upload(inputStream);
        pushGoodsMapper.updatePicture(headshot,pushId);
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success();
    }

    /**
     * 查找店铺推文
     * @param shopId
     * @return
     */
    @Override
    public ResultSet selectTweets(Integer shopId) {
        List<Tweets> tweets = tweetsMapper.selectByShopId(shopId);
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        List<ShopTweets> tweetsList = new ArrayList<>();
        if (tweets != null){
            for (Tweets tweet : tweets) {
                tweetsList.add(new ShopTweets(tweet.getId(),tweet.getShopId(),shopMapper.selectById(tweet.getShopId()).getName(),tweet.getTweets()));
            }
        }
        return ResultSet.success(tweetsList,null);
    }

    /**
     * 删除推文
     * @param shopTweets
     * @return
     */
    @Override
    public ResultSet deleteTweets(ShopTweets shopTweets) {
        tweetsMapper.deleteById(shopTweets.getId());
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success();
    }

    /**
     * 查看店铺信息
     * @param shopId
     * @return
     */
    @Override
    public ResultSet selectMessage(Integer shopId) {
        List<ShopMessage> shopMessages = shopMessageMapper.selectByShopId(shopId);
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(shopMessages,null);
    }

    /**
     * 删除店铺信息
     * @param shopMessage
     * @return
     */
    @Override
    public ResultSet deleteMessage(ShopMessage shopMessage) {
        ResultSet resultSet = null;
        if (shopMessageMapper.deleteById(shopMessage.getId()) == 1) {
            resultSet = ResultSet.success();
            SqlSessionUtil.commit();
        }else {
            resultSet = ResultSet.error();
            SqlSessionUtil.rollback();
        }
        SqlSessionUtil.close();
        return resultSet;
    }
}
