package com.yinjunbiao.service.Impl;


import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;


import com.yinjunbiao.entity.*;
import com.yinjunbiao.mapper.*;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.pojo.ShoppingCart;
import com.yinjunbiao.service.UserService;
import com.yinjunbiao.util.CONST;
import com.yinjunbiao.util.SqlSessionUtil;

import java.util.ArrayList;
import java.util.List;

@Component("userService")
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

    @Autowired
    private SubscribleMapper subscribleMapper;

    @Autowired
    private ConsultationMapper consultationMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private ReportMapper reportMapper;

    /**
     * 登录
     * @param user 用户信息
     * @return 结果集
     */
    @Override
    public ResultSet login(User user) {
        User select = userMapper.selectByPhone(user.getPhone());
        ResultSet resultSet = null;
        //双重验证登录
        if (select == null) {
            resultSet = ResultSet.error("userName", "账号不存在");
        } else {
            int cnt = select.getCnt();
            if (cnt > 0) {
                synchronized (userMapper) {
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

    /**
     * 注册
     * @param user 用户信息
     * @return
     */
    @Override
    public ResultSet register(User user) {
        ResultSet resultSet = null;
        //判断用户名和地址是否含有敏感词
        for (String s : CONST.SENSITIVE) {
            if (user.getUserName().contains(s) || user.getAddress().contains(s)) {
                return ResultSet.error("userName", "用户名含有敏感字");
            }
        }
            int res;
            if (userMapper.selectByPhone(user.getUserName()) != null) {
                resultSet = ResultSet.error("userName", "用户名已被注册");
            }else {
                synchronized (userMapper) {
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

    /**
     * 改密码
     * @param user 用户信息
     * @return 结果集
     */
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

    /**
     * 改手机号
     * @param user
     * @return
     */
    @Override
    public ResultSet changePhone(User user) {
        //判断格式
        ResultSet resultSet = null;
        String regex = "1[34578][0-9]{9}";
        if (!user.getPhone().matches(regex)){
            return ResultSet.error(user.getPhone(),"请输入正确的手机号");
        }
        //密码验证
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

    /**
     * 改头像
     * @param headshot 头像的url
     * @param id 用户id
     * @return
     */
    @Override
    public ResultSet changeHeadshot(String headshot, Integer id) {
        if (userMapper.updateHeadshot(headshot,id) == 1) {
            SqlSessionUtil.commit();
            SqlSessionUtil.close();
            return ResultSet.success(null,"修改成功");
        }
        return ResultSet.error();
    }

    /**
     * 修改用户个人信息
     * @param user 用户信息
     * @return 是否成功
     */
    @Override
    public ResultSet changeMessage(User user) {
        ResultSet resultSet = null;
        //判断敏感词
        for (String s : CONST.SENSITIVE) {
            if (user.getAddress().contains(s) || user.getUserName().contains(s)) {
                resultSet =  ResultSet.error("userName", "用户名或地址含有敏感字");
            }
        }
        if (resultSet == null){
            userMapper.updateMessage(user.getUserName(),user.getAddress(),user.getIsPrivate());
        }
        resultSet = ResultSet.success(null,"信息修改成功");
        return resultSet;
    }


    /**
     * 申请称为商家
     * @param id 申请用户id
     * @return 是狗成功
     */
    @Override
    public ResultSet applyShop(Integer id) {
        ResultSet resultSet = null;
        //判断是否重复申请
        if (userMapper.selectById(id).getIdentify() == 1 || applyMapper.selectByUserId(id).getStatus() == 0){
            resultSet = ResultSet.error(null,"请勿重复申请");
        }else if (applyMapper.insert(id) == 1){
            resultSet = ResultSet.success(null,"申请成功");
        }else {
            resultSet = ResultSet.error();
        }
        return resultSet;
    }

    /**
     * 查找购物车用户的
     * @param id 查找的用户
     * @return 返回购物车信息
     */
    @Override
    public ResultSet selectMyShoppingCart(Integer id) {
        List<Cart> carts = cartMapper.selectByUserId(id);
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        for (Cart cart : carts) {
            Goods goods = goodsMapper.selectById(cart.getGoodsId());
            shoppingCarts.add(new ShoppingCart(cart.getId(),cart.getUserId(),goods.getName(),shopMapper.selectById(goods.getShopId()).getName(),cart.getNumber()));
        }
        return ResultSet.success(shoppingCarts,"查询成功");
    }

    /**
     * 添加到购物车
     * @param cart
     * @return
     */
    @Override
    public ResultSet addShoppingCart(Cart cart) {
        Cart shoppingCart = cartMapper.selectByUAGId(cart.getUserId(), cart.getGoodsId());
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
    public ResultSet changeShoppingCart(Cart cart) {
        if (cart.getNumber() == 0){
            cartMapper.deleteById(cart.getId());
        }else {
            cartMapper.updateNumber(cart.getNumber(),cart.getId());
        }
        return ResultSet.success(null,"修改成功");
    }

    @Override
    public ResultSet buyCart(Cart cart) {
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
        List<Cart> shoppingCarts = cartMapper.selectByUserId(id);
        ResultSet resultSet = null;
        for (Cart shoppingCart : shoppingCarts) {
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

    @Override
    public ResultSet selectSub(Subscrible subscrible) {
        Subscrible select = subscribleMapper.selectByUASId(subscrible.getUserId(), subscrible.getShopId());
        ResultSet resultSet = null;
        if (select == null){
            resultSet = ResultSet.error();
        }
        if (resultSet == null){
            resultSet = ResultSet.success();
        }
        return resultSet;
    }



    @Override
    public ResultSet subscrible(Subscrible subscrible) {
        ResultSet resultSet = null;
        if (subscribleMapper.selectByUASId(subscrible.getUserId(),subscrible.getShopId()) == null){
            synchronized (subscribleMapper){
                if (subscribleMapper.selectByUASId(subscrible.getUserId(),subscrible.getShopId()) == null){
                    subscribleMapper.insert(subscrible.getUserId(),subscrible.getShopId());
                    resultSet = ResultSet.success(null,"订阅成功");
                }else {
                    subscribleMapper.deleteByUASId(subscrible.getUserId(),subscrible.getShopId());
                }
            }
        }else {
            subscribleMapper.deleteByUASId(subscrible.getUserId(),subscrible.getShopId());
        }
        if (resultSet == null){
            resultSet = ResultSet.success(null,"取消订阅成功");
        }
        return resultSet;
    }

    @Override
    public ResultSet selectMySubs(Integer userId) {
        List<Subscrible> subscribles = subscribleMapper.selectbyUserId(userId);
        return ResultSet.success(subscribles,"查询成功");
    }

    @Override
    public ResultSet sendConsultation(Consultation consultation) {
        consultationMapper.insert(consultation.getGoodsId(),consultation.getConsultation(),consultation.getUserId());
        return ResultSet.success();
    }

    @Override
    public ResultSet deleteConsultation(Long id) {
        int delete = consultationMapper.deleteById(id);
        return delete == 1 ? ResultSet.success(null,"删除成功") : ResultSet.error(null,"评论不存在，可能已经被删除");
    }

    @Override
    public ResultSet sendReply(Reply reply) {
        int insert = replyMapper.insert(reply.getConsultationId(), reply.getReply(), reply.getUserId());
        return insert == 1 ? ResultSet.success(null,"发送成功") : ResultSet.error(null,"发送异常");
    }

    @Override
    public ResultSet deleteReply(Long id) {
        int delete = replyMapper.deleteById(id);
        return delete == 1 ? ResultSet.success(null,"删除成功") : ResultSet.error(null,"回复不存在，可能已经删除");
    }

    @Override
    public ResultSet reportGoods(Report report) {
        ResultSet resultSet = null;
        if (reportMapper.selectByUAGId(report.getUserId(), report.getGoodId()) == null){
            synchronized (reportMapper){
                if (reportMapper.selectByUAGId(report.getUserId(),report.getGoodId()) == null){
                    reportMapper.insert(report.getGoodId(),report.getUserId(),report.getStatus(),report.getDescription());
                    resultSet = ResultSet.success(null,"举报成功");
                }
            }
        }
        if (resultSet == null){
            resultSet = ResultSet.error(null,"请勿重复举报");
        }
        return resultSet;
    }
}
