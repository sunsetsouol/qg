package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.*;
import com.yinjunbiao.mapper.*;
import com.yinjunbiao.pojo.*;
import com.yinjunbiao.service.ManagerService;
import com.yinjunbiao.util.SqlSessionUtil;

import java.util.ArrayList;
import java.util.List;

@Component("managerService")
@Scope("singleton")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private PushGoodsMapper pushGoodsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ConsultationMapper consultationMapper;

    @Autowired
    private UserMessageMapper userMessageMapper;

    @Autowired
    private ShopMessageMapper shopMessageMapper;

    @Autowired
    private RefundMapper refundMapper;


    /**
     * 删除货物
     * @param id
     * @return
     */
    @Override
    public ResultSet deleteGoods(Long id) {
        ResultSet resultSet = null;
        Goods goods = goodsMapper.selectById(id);
        cartMapper.deleteByGoodsId(id);

        synchronized (goodsMapper){
            if (goodsMapper.deleteById(id) == 1) {
                List<Orders> orders = ordersMapper.selectByGoodsId(id);
                if (orders != null){
                    for (Orders order : orders) {
                        refundMapper.deleteByOrderId(order.getId());
                    }
                }
                ordersMapper.deleteByGoodsId(id);
                shopMessageMapper.insert(goods.getShopId(),"商品\""+goods.getName()+"\"已被下架");
                reportMapper.updateStatusByGoodsId(1,goods.getId());
                SqlSessionUtil.commit();
                resultSet = ResultSet.success();
            }else {
                SqlSessionUtil.rollback();
                resultSet= ResultSet.error();
            }
        }
        SqlSessionUtil.close();
        return resultSet;
    }

    /**
     * 删除回复
     * @param goodsReply
     * @return
     */
    @Override
    public ResultSet deleteReply(GoodsReply goodsReply) {
        Reply reply = replyMapper.selectById(goodsReply.getId());
        ResultSet resultSet = null;
        if (reply != null){
            synchronized (replyMapper){
                if (replyMapper.deleteById(goodsReply.getId()) == 1) {
                    userMessageMapper.insert(reply.getUserId(),"你的回复\""+reply.getReply()+"\"已被删除");
                    SqlSessionUtil.commit();
                    resultSet = ResultSet.success();
                }
            }
        }
        if (resultSet == null){
            resultSet =ResultSet.error();
            SqlSessionUtil.rollback();
        }
        SqlSessionUtil.close();
        return resultSet;
    }

    /**
     * 删除评论
     * @param goodsConsultations
     * @return
     */
    @Override
    public ResultSet deleteConsultations(GoodsConsultations goodsConsultations) {
        ResultSet resultSet = null;
        Consultation consultation = consultationMapper.selectById(goodsConsultations.getId());
        if (consultation != null){
            synchronized (consultationMapper){
                replyMapper.deleteByConsultationId(goodsConsultations.getId());
                if (consultationMapper.deleteById(goodsConsultations.getId()) == 1) {
                    userMessageMapper.insert(consultation.getUserId(),"你的评论\""+consultation.getConsultation()+"\"已被删除");
                    SqlSessionUtil.commit();
                    resultSet = ResultSet.success();
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

    @Override
    public ResultSet selectShopApply() {
        List<Apply> applies = applyMapper.select();
        List<ShopApply> shopApplies = new ArrayList<>();
        if (applies != null){
            for (Apply apply : applies) {
                User user = userMapper.selectById(apply.getUserId());
                if(user != null){
                    shopApplies.add(new ShopApply(apply.getId(),apply.getUserId(),user.getUserName(),apply.getShopName(),apply.getDescription()));
                }
            }
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(shopApplies,null);
    }

    /**
     * 同意店铺注册申请
     * @param apply
     * @return
     */
    @Override
    public ResultSet agreeShopApply(Apply apply) {
        Apply apply1 = applyMapper.selectByUserId(apply.getUserId());
        ResultSet resultSet = null;
        if(apply1 != null){
            synchronized (shopMapper){
                Shop shop = shopMapper.selectByBossId(apply1.getUserId());
                if (shop == null){
                    applyMapper.updateStatus(1,apply.getId());
                    userMapper.updateIdentify(1,apply1.getUserId());
                    shopMapper.insert(apply1.getUserId(),apply1.getShopName(),apply1.getDescription());
                    SqlSessionUtil.commit();
                    resultSet = ResultSet.success();
                }
            }
        }
        if (resultSet == null){
            SqlSessionUtil.rollback();
            resultSet = ResultSet.error();
        }
        SqlSessionUtil.close();
        return resultSet;
    }

    /**
     * 不同意店铺申请
     * @param apply
     * @return
     */
    @Override
    public ResultSet disagreeShopApply(Apply apply) {
        applyMapper.updateStatus(2,apply.getId());
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success();
    }

    /**
     * 查找店铺申请
     * @return
     */
    @Override
    public ResultSet selectPushApply() {
        List<PushGood> select = pushGoodsMapper.select();
        SqlSessionUtil.close();
        return ResultSet.success(select,null);
    }

    /**
     * 同意货物推送
     * @param pushGood
     * @return
     */
    @Override
    public ResultSet agreePushGoods(PushGood pushGood) {
        ResultSet resultSet = null;
        PushGood pushGood1 = pushGoodsMapper.selectById(pushGood.getId());
        if (pushGood1 != null && pushGood1.getStatus() == 0){
            synchronized (pushGoodsMapper){
                pushGood1 = pushGoodsMapper.selectById(pushGood.getId());
                if (pushGood1 != null && pushGood1.getStatus() == 0){
                    pushGoodsMapper.updateStatus(1,pushGood.getId());
                    goodsMapper.insert(pushGood1.getShopId(),shopMapper.selectById(pushGood1.getShopId()).getName(),pushGood1.getDescription(),pushGood1.getInventory(),pushGood1.getPrice(),pushGood1.getPicture(),pushGood1.getName());
                    resultSet = ResultSet.success();
                    SqlSessionUtil.commit();
                }
            }
        }
        if (resultSet == null){
            SqlSessionUtil.rollback();
            resultSet = ResultSet.error();
        }
        SqlSessionUtil.close();
        return resultSet;
    }

    /**
     * 同意货物推送
     * @param pushGood
     * @return
     */
    @Override
    public ResultSet disagreePushGoods(PushGood pushGood) {
        ResultSet resultSet = null;
        if ( pushGoodsMapper.selectById(pushGood.getId()) != null){
            pushGoodsMapper.updateStatus(2,pushGood.getId());
            SqlSessionUtil.commit();
            resultSet = ResultSet.success();
        }else {
            SqlSessionUtil.rollback();
            resultSet = ResultSet.error();
        }
        SqlSessionUtil.close();
        return resultSet;
    }


    /**
     * 查找举报申请
     * @return
     */
    @Override
    public ResultSet selectReport() {
        List<Report> reports = reportMapper.select();
        ResultSet resultSet = null;
        if (reports != null && reports.size() > 0){
            List<GoodsReport> goodsReports = new ArrayList<>();
            if (reports != null){
                for (Report report : reports) {
                    goodsReports.add(new GoodsReport(report.getId(),report.getGoodsId(),goodsMapper.selectById(report.getGoodsId()).getName(),report.getDescription()));
                }
            }
            resultSet = ResultSet.success(goodsReports,"查找成功");
            SqlSessionUtil.commit();
        }
        if (resultSet == null){
            resultSet = ResultSet.success();
            SqlSessionUtil.rollback();
        }
        SqlSessionUtil.close();
        return resultSet;
    }


    /**
     * 不同意下架
     * @param report
     * @return
     */
    @Override
    public ResultSet disagreeReport(Report report) {
        reportMapper.updateStatusByGoodsId(2,report.getGoodsId());
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success();
    }

}
