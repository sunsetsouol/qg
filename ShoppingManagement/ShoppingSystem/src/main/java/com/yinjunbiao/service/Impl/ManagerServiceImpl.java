package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.Apply;
import com.yinjunbiao.entity.PushGood;
import com.yinjunbiao.entity.Shop;
import com.yinjunbiao.entity.User;
import com.yinjunbiao.mapper.*;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.ShopApply;
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
            applyMapper.updateStatus(1,apply.getId());
            userMapper.updateIdentify(1,apply1.getUserId());
            synchronized (shopMapper){
                Shop shop = shopMapper.selectByBossId(apply1.getUserId());
                if (shop == null){
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



}
