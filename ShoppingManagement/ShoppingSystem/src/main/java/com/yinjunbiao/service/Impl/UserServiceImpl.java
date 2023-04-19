package com.yinjunbiao.service.Impl;


import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;


import com.yinjunbiao.entity.*;
import com.yinjunbiao.mapper.*;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.UserService;
import com.yinjunbiao.util.CONST;
import com.yinjunbiao.util.SqlSessionUtil;

import java.util.List;

@Component("service")
@Scope("singleton")
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RefundMapper refundMapper;

    @Override
    public ResultSet login(User user) {
        User select = userMapper.selectByPhone(user.getPhone());
        ResultSet resultSet = null;
        if (select == null) {
            resultSet = ResultSet.error("userName", "账号不存在");
        } else {
            int cnt = select.getCnt();
            if (cnt > 0) {
                synchronized (UserService.class) {
                    cnt = userMapper.selectByPhone(user.getPhone()).getCnt();
                    if (cnt > 0) {
                        select = userMapper.selectByPhone(user.getPhone());
                        if (select != null && select.getPassword().equals(user.getPassword())) {
                            resultSet = ResultSet.success(select, "登录成功");
                        } else {
                            resultSet = ResultSet.error(select, "账号不存在或密码错误");
                            userMapper.updateCnt(userMapper.selectByPhone(user.getPhone()).getCnt() - 1, user.getPhone());
                            SqlSessionUtil.commit();
                            SqlSessionUtil.close();
                        }
                    }
                }
            } else {
                resultSet = ResultSet.error(null, "登录失败次数过多，请稍后重试");
            }
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }

    @Override
    public ResultSet register(User user) {
        ResultSet resultSet = null;
        for (String s : CONST.SENSITIVE) {
            if (user.getUserName().contains(s) || user.getAddress().contains(s)) {
                return ResultSet.error("userName", "用户名含有敏感字");
            }
        }
            int res;
            if (userMapper.selectByPhone(user.getUserName()) != null) {
                resultSet = ResultSet.error("userName", "用户名已被注册");
            }else {
                synchronized (UserServiceImpl.class) {
                    if (userMapper.selectByPhone(user.getPhone()) != null) {
                        resultSet = ResultSet.error("userName", "用户名已被注册");
                    } else if (user.getUserName().length() > 20 || user.getPassword().length() > 20 || user.getAddress().length() > 50) {
                        resultSet = ResultSet.error(null, "账号跟密码长度不能超过20个字符,地址不能超过五十个字符");
                    } else {
                        res = userMapper.insert(user.getPhone(),user.getUserName(),user.getAddress(),user.getPassword(),user.getIsPrivate());
                        if (res == 1) {
                            resultSet = ResultSet.success(res, "注册成功");
                        }
                    }
                }
            }

        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }

    @Override
    public ResultSet changePassword(User user) {
        User select = userMapper.selectByPhone(user.getPhone());
        ResultSet resultSet = null;
        int res = 0;
        if (select == null) {
            resultSet = ResultSet.error("phone", "该手机号尚未注册");
        } else if (select.getPassword().equals(user.getPassword())) {
            resultSet = ResultSet.error("password", "不能修改与原来相同的密码");
        } else {
            res = userMapper.updatePassword(user.getPassword(), user.getId());
        }
        if (res == 1) {
            resultSet = ResultSet.success(null, "修改成功");
        }

        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }

    @Override
    public ResultSet changePhone(User user) {
        ResultSet resultSet = null;
        String regex = "1[34578][0-9]{9}";
        if (!user.getPhone().matches(regex)){
            return ResultSet.error(user.getPhone(),"请输入正确的手机号");
        }
        User select = userMapper.selectById(user.getId());
        if (!user.getPassword().equals(select.getPassword())){
            resultSet = ResultSet.error("password","密码错误");
        }else if (select == null || select.getPhone().equals(user.getPhone())){
            resultSet = ResultSet.error("phone","不能修改与原来相同的手机号");
        } else if (userMapper.updatePhone(user.getPhone(),user.getId()) == 1) {
            SqlSessionUtil.commit();
            resultSet = ResultSet.success(null,"手机号修改成功");
        }
        SqlSessionUtil.close();
        return resultSet;
    }

    @Override
    public ResultSet changeAddress(String address, Integer id) {
        ResultSet resultSet = null;
        for (String s : CONST.SENSITIVE) {
            if (address.contains(s)) {
                return ResultSet.error("userName", "地址含有敏感字");
            }
        }
        if (userMapper.updateAddress(address,id) == 1) {
            resultSet = ResultSet.success(null,"修改成功");
            SqlSessionUtil.commit();
            SqlSessionUtil.close();
        }
        return resultSet;
    }

    @Override
    public ResultSet changeHeadshot(String headshot, Integer id) {
        if (userMapper.updateHeadshot(headshot,id) == 1) {
            SqlSessionUtil.commit();
            SqlSessionUtil.close();
            return ResultSet.success(null,"修改成功");
        }
        return ResultSet.error();
    }

    @Override
    public ResultSet changePrivate(Integer isPrivate, Integer id) {
        if (userMapper.updateIsPrivate(isPrivate,id) == 1) {
            SqlSessionUtil.commit();
            SqlSessionUtil.close();
            return ResultSet.success(null,"修改成功");
        }
        return ResultSet.error();
    }

    @Override
    public ResultSet changeUserName(String userName, Integer id) {
        for (String s : CONST.SENSITIVE) {
            if (userName.contains(s)){
                return ResultSet.error("userName","用户名含敏感词");
            }
        }
        if (userMapper.updateUserName(userName,id) == 1) {
            SqlSessionUtil.commit();
            SqlSessionUtil.close();
            return ResultSet.success(null,"修改成功");
        }
        return ResultSet.error();
    }

    @Override
    public ResultSet applyShop(Integer id) {
        ResultSet resultSet = null;
        if (userMapper.selectById(id).getIdentify() == 1 || applyMapper.selectByUserId(id).getStatus() == 0){
            resultSet = ResultSet.error(null,"请勿重复申请");
        }else if (applyMapper.insert(id) == 1){
            resultSet = ResultSet.success(null,"申请成功");
        }else {
            resultSet = ResultSet.error();
        }
        return resultSet;
    }

    @Override
    public ResultSet selectMyShoppingCart(Integer id) {
        List<ShoppingCart> shoppingCarts = cartMapper.selectByUserId(id);
        return ResultSet.success(shoppingCarts,"查询成功");
    }

    @Override
    public ResultSet addShoppingCart(ShoppingCart cart) {
        ShoppingCart shoppingCart = cartMapper.selectByUAGId(cart.getUserId(), cart.getGoodsId());
        ResultSet resultSet = null;
        if (shoppingCart == null){
            synchronized (cartMapper){
                shoppingCart = cartMapper.selectByUAGId(cart.getUserId(),cart.getGoodsId());
                if (shoppingCart == null){
                    if (cartMapper.insert(cart.getGoodsId(),cart.getNumber(),cart.getUserId()) == 1) {
                        resultSet = ResultSet.success(null,"插入成功");
                    }
                }
            }
        }
        if (resultSet == null) {
            cart.setNumber(shoppingCart.getNumber() + cart.getNumber());
            resultSet = changeShoppingCart(cart);
        }
        return resultSet;
    }

    @Override
    public ResultSet delectShoppingCart(Long id) {
        cartMapper.deleteById(id);
        return ResultSet.success(null,"删除成功");
    }

    @Override
    public ResultSet changeShoppingCart(ShoppingCart cart) {
        if (cart.getNumber() == 0){
            cartMapper.deleteById(cart.getId());
        }else {
            cartMapper.updateNumber(cart.getNumber(),cart.getId());
        }
        return ResultSet.success(null,"修改成功");
    }

    @Override
    public ResultSet buyCart(ShoppingCart cart) {
        Goods goods = goodsMapper.selectById(cart.getGoodsId());
        ResultSet resultSet = null;
        if (goods.getInventory() < cart.getNumber()){
            resultSet = ResultSet.error(null,"库存不足");
        }else {
            synchronized (orderMapper){
                goods = goodsMapper.selectById(cart.getGoodsId());
                    if (goods.getInventory() < cart.getNumber()){
                        resultSet = ResultSet.error(null,"库存不足");
                    }else {
                        orderMapper.insert(System.currentTimeMillis(),userMapper.selectById(shopMapper.selectById(goods.getShopId()).getBossId()).getAddress(),userMapper.selectById(cart.getUserId()).getAddress(),cart.getGoodsId(),cart.getUserId(),cart.getNumber());
                        cartMapper.deleteById(cart.getId());
                        resultSet = ResultSet.success();
                    }
            }
        }
        if (resultSet.getCode() == 1){
            SqlSessionUtil.commit();
        }else {
            SqlSessionUtil.rollback();
        }
        SqlSessionUtil.close();
        return resultSet;
    }

    @Override
    public ResultSet buyAllcart(Integer id) {
        List<ShoppingCart> shoppingCarts = cartMapper.selectByUserId(id);
        ResultSet resultSet = null;
        for (ShoppingCart shoppingCart : shoppingCarts) {
            resultSet = buyCart(shoppingCart);
            if (resultSet.getCode() != 1) {
                SqlSessionUtil.rollback();
                break;
            }
        }
        SqlSessionUtil.commit();
        if (resultSet == null){
            resultSet = ResultSet.success();
        }
        return resultSet;
    }

    @Override
    public ResultSet buy(Orders orders) {
        ResultSet resultSet = null;
        Goods goods = goodsMapper.selectById(orders.getGoodsId());
        if (orders.getNumber() > goods.getInventory()){
            resultSet = ResultSet.error(null,"库存不足");
        }else {
            synchronized (orderMapper){
                goods = goodsMapper.selectById(orders.getGoodsId());
                if (orders.getNumber() > goods.getInventory()){
                    resultSet = ResultSet.error(null,"库存不足");
                }else {
                    orderMapper.insert(System.currentTimeMillis(),userMapper.selectById(shopMapper.selectById(goods.getShopId()).getBossId()).getAddress(),userMapper.selectById(orders.getUserId()).getAddress(),orders.getGoodsId(),orders.getUserId(),orders.getNumber());
                    resultSet = ResultSet.success();
                }
            }
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }

    @Override
    public ResultSet refund(Refund refund) {
        //查找订单
        Orders select = orderMapper.select(refund.getOrderId());
        ResultSet resultSet = null;
        if (select != null ){
            //订单存在且没有重复申请
            List<Refund> refunds = refundMapper.selectByOrderId(select.getId());
            //超过两次就不能继续申请了
            if (refunds == null || (refunds.size() < 2 && refunds.get(0).getStatus() != 0)){
                synchronized (refundMapper){
                    refunds = refundMapper.selectByOrderId(select.getId());
                    if (refunds == null || (refunds.size() < 2 && refunds.get(0).getStatus() != 0)){
                        refundMapper.insert(select.getId(),refund.getCause(),refund.getDescription());
                        resultSet = ResultSet.success();
                    }
                }
            }
        }
        if (resultSet == null){
            resultSet = ResultSet.error(null,"退款申请次数过多");
        }
        return resultSet;
    }
}
