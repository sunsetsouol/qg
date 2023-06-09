package com.yinjunbiao.service.Impl;


import com.yinjunbiao.MyORM.SqlSession.SqlSession;
import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;


import com.yinjunbiao.entity.*;
import com.yinjunbiao.mapper.*;
import com.yinjunbiao.pojo.*;
import com.yinjunbiao.service.UserService;
import com.yinjunbiao.util.CONST;
import com.yinjunbiao.util.Md5Util;
import com.yinjunbiao.util.SqlSessionUtil;
import com.yinjunbiao.util.UploadUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.*;

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
    private OrdersMapper ordersMapper;

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

    @Autowired
    private TweetsMapper tweetsMapper;

    @Autowired
    private UserMessageMapper userMessageMapper;


    static {

        UserMapper mapper = SqlSessionUtil.getSqlSession().getMapper(UserMapper.class);

        ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(3,
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        pool.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mapper.updateCnt();
                SqlSessionUtil.commit();
                SqlSessionUtil.close();
            }
        }, 5, 5, TimeUnit.MINUTES);

    }

    /**
     * 登录
     *
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
                        if (select != null && Md5Util.matches(user.getPassword(), select.getPassword())) {
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
    public ResultSet getHeadshot(Integer id) {
        User user = userMapper.selectById(id);
        SqlSessionUtil.close();
        return ResultSet.success(user.getHeadshot(), null);
    }

    /**
     * 注册
     *
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
        if (userMapper.selectByPhone(user.getPhone()) != null) {
            resultSet = ResultSet.error("phone", "手机号已被注册");
        } else if (user.getUserName().length() > 20 || user.getPassword().length() > 20 || user.getAddress().length() > 50) {
            resultSet = ResultSet.error(null, "账号跟密码长度不能超过20个字符,地址不能超过五十个字符");
        } else {
            user.setPassword(Md5Util.encode(user.getPassword()));
            synchronized (userMapper) {
                if (userMapper.selectByPhone(user.getPhone()) != null) {
                    resultSet = ResultSet.error("phone", "手机号已被注册");
                } else {
                    res = userMapper.insert(user.getPhone(), user.getUserName(), user.getAddress(), user.getPassword(), user.getIsPrivate());
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
     *
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
        } else if (Md5Util.matches(user.getPassword(),select.getPassword())) {
            resultSet = ResultSet.error("password", "不能修改与原来相同的密码");
        } else {
            res = userMapper.updatePassword(Md5Util.encode(user.getPassword()), select.getId());
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
     *
     * @param user
     * @return
     */
    @Override
    public ResultSet changePhone(User user) {
        //判断格式
        ResultSet resultSet = null;
        String regex = "1[34578][0-9]{9}";
        if (!user.getPhone().matches(regex)) {
            return ResultSet.error(user.getPhone(), "请输入正确的手机号");
        }
        //密码验证
        User select = userMapper.selectById(user.getId());
        if (!Md5Util.matches(user.getPassword(),select.getPassword())) {
            resultSet = ResultSet.error("password", "密码错误");
        } else if (select == null || select.getPhone().equals(user.getPhone())) {
            resultSet = ResultSet.error("phone", "不能修改与原来相同的手机号");
        } else if (userMapper.updatePhone(user.getPhone(), user.getId()) == 1) {
            SqlSessionUtil.commit();
            resultSet = ResultSet.success(null, "手机号修改成功");
        }
        SqlSessionUtil.close();
        return resultSet;
    }

    /**
     * 改头像
     *
     * @param inputStream
     * @param id
     * @return
     */
    @Override
    public ResultSet changeHeadshot(InputStream inputStream, Integer id) {
        ResultSet resultSet = null;

        String headshot = UploadUtil.upload(inputStream);

        if (userMapper.updateHeadshot(headshot, id) == 1) {
            SqlSessionUtil.commit();
            resultSet = ResultSet.success(null, "修改成功");
        } else {
            SqlSessionUtil.rollback();
            resultSet = ResultSet.error();
        }
        SqlSessionUtil.close();
        return resultSet;
    }


    /**
     * 查找购物车用户的
     *
     * @param id 查找的用户
     * @return 返回购物车信息
     */
    @Override
    public ResultSet selectMyShoppingCart(Integer id, Integer currentPage, Integer pageSize) {
        List<Cart> carts = cartMapper.selectByUserId(id, (currentPage - 1) * pageSize, pageSize);
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        if (carts != null){
            for (Cart cart : carts) {
                Goods goods = goodsMapper.selectById(cart.getGoodsId());
                shoppingCarts.add(new ShoppingCart(cart.getId(), goods.getName(), cart.getNumber(), goods.getPicture(), goods.getPrice(), goods.getShopName()));
            }
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(shoppingCarts, "查询成功");
    }


    @Override
    public ResultSet changeShoppingCart(Cart cart) {
        if (cart.getNumber() == 0) {
            cartMapper.deleteById(cart.getId());
        } else {
            cartMapper.updateNumber(cart.getNumber(), cart.getId());
        }
        return ResultSet.success(null, "修改成功");
    }

    /**
     * 添加到购物车
     */
    @Override
    public ResultSet addCart(Cart cart) {
        Cart shoppingCart = cartMapper.selectByUAGId(cart.getUserId(), cart.getGoodsId());
        ResultSet resultSet = null;
        if (shoppingCart == null) {
            synchronized (cartMapper) {
                shoppingCart = cartMapper.selectByUAGId(cart.getUserId(), cart.getGoodsId());
                if (shoppingCart == null) {
                    if (cartMapper.insert(cart.getGoodsId(), cart.getShopId(), cart.getNumber(), cart.getUserId(), cart.getSinglePrice()) == 1) {
                        resultSet = ResultSet.success(null, "插入成功");
                    }
                }
            }
        }
        if (resultSet == null) {
            shoppingCart.setNumber(shoppingCart.getNumber() + cart.getNumber());
            resultSet = changeShoppingCart(shoppingCart);
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }



    /**
     * 申请称为商家
     *
     * @param apply 用户发送的申请信息
     * @return 是狗成功
     */
    @Override
    public ResultSet applyShop(Apply apply) {
        ResultSet resultSet = null;
        Apply apply1 = applyMapper.selectByUserId(apply.getUserId());
        //判断是否重复申请
        if (userMapper.selectById(apply.getUserId()).getIdentify() != 1) {
            if (apply1 == null || apply1.getStatus() != 0) {
                synchronized (applyMapper) {
                    if (userMapper.selectById(apply.getUserId()).getIdentify() != 1) {
                        apply1 = applyMapper.selectByUserId(apply.getUserId());
                        if (apply1 == null || apply1.getStatus() != 0) {
                            applyMapper.insert(apply.getUserId(), apply.getShopName(), apply.getDescription());
                            resultSet = ResultSet.success(null, "申请成功");
                            SqlSessionUtil.commit();
                        }
                    }
                }
            }
        }
        if (resultSet == null) {
            SqlSessionUtil.rollback();
            resultSet = ResultSet.error(null, "请勿重复申请");
        }
        SqlSessionUtil.close();
        return resultSet;
    }

    @Override
    public ResultSet selectMyShop(Integer id) {
        Shop shop = shopMapper.selectByBossId(id);
        ResultSet resultSet = null;
        if (shop == null) {
            resultSet = ResultSet.error();
        } else {
            resultSet = ResultSet.success(shop.getId(), null);
        }
        SqlSessionUtil.close();
        return resultSet;
    }

    @Override
    public ResultSet selectMyOrders(Integer userId, Integer status, Integer currentPage, Integer pageSize) {
        List<Orders> orders = ordersMapper.selectByUserId(userId, status, (currentPage - 1) * pageSize, pageSize);
        List<ShopOrders> shopOrders = new ArrayList<>();
        if (orders != null){
            for (Orders order : orders) {
                Goods goods = goodsMapper.selectById(order.getGoodsId());
                if (goods != null) {
                    ShopOrders shopOrder = new ShopOrders(order.getId(), CONST.dateFormat.format(order.getTime()), order.getSendAddress(), order.getReceiveAddress(), goods.getName(), order.getStatus(), userMapper.selectById(order.getUserId()).getUserName(), order.getNumber(), goods.getShopName(), goods.getPrice());
                    shopOrders.add(shopOrder);
                }
            }
        }
        SqlSessionUtil.close();
        return ResultSet.success(shopOrders, null);
    }


    /**
     * 根据id删除购物车
     *
     * @param ids
     * @return
     */
    @Override
    public ResultSet delectCarts(Long[] ids) {
        for (Long id : ids) {
            cartMapper.deleteById(id);
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(null, "删除成功");
    }

    /**
     * 购买购物车的商品
     *
     * @param ids
     * @return
     */
    @Override
    public ResultSet buyCarts(Long[] ids) {
        ResultSet resultSet = null;
        if (ids != null){
            for (Long id : ids) {
                Cart cart = cartMapper.selectById(id);
                Goods goods = goodsMapper.selectById(cart.getGoodsId());
                if (goods.getInventory() >= cart.getNumber()) {
                    synchronized (ordersMapper) {
                        goods = goodsMapper.selectById(cart.getGoodsId());
                        if (cart.getUserId().equals(shopMapper.selectById(goods.getShopId()).getBossId())) {
                            resultSet = ResultSet.error(null, "不可以购买自己的店铺的商品");
                        }
                        if (goods.getInventory() >= cart.getNumber()) {
                            ordersMapper.insert(System.currentTimeMillis(), userMapper.selectById(shopMapper.selectById(cart.getShopId()).getBossId()).getAddress(), userMapper.selectById(cart.getUserId()).getAddress(), cart.getGoodsId(), cart.getShopId(), cart.getUserId(), cart.getNumber(), cart.getSinglePrice());
                            shopMapper.updateSales(shopMapper.selectById(cart.getShopId()).getSales()+cart.getNumber(),cart.getShopId());
                            cartMapper.deleteById(cart.getId());
                        } else {
                            resultSet = ResultSet.error(null, "库存不足");
                            break;
                        }
                    }
                } else {
                    resultSet = ResultSet.error();
                    break;
                }
            }
        }
        if (resultSet == null) {
            resultSet = ResultSet.success();
            SqlSessionUtil.commit();
        } else {
            SqlSessionUtil.rollback();
        }
        SqlSessionUtil.close();
        return resultSet;
    }

    /**
     * 查询用户
     *
     * @param user
     * @return
     */
    @Override
    public ResultSet selectUser(User user) {
        String userName = "%" + user.getUserName() + "%";
        List<User> users = userMapper.selectByName(userName);
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(users, null);
    }


    /**
     * 订阅
     *
     * @param subscrible
     * @return
     */
    @Override
    public ResultSet subscrible(Subscrible subscrible) {
        ResultSet resultSet = null;
        if (subscribleMapper.selectByUASId(subscrible.getUserId(), subscrible.getShopId()) == null) {
            synchronized (subscribleMapper) {
                if (subscribleMapper.selectByUASId(subscrible.getUserId(), subscrible.getShopId()) == null) {
                    subscribleMapper.insert(subscrible.getUserId(), subscrible.getShopId());
                    synchronized (shopMapper) {
                        Shop shop = shopMapper.selectById(subscrible.getShopId());
                        if (shop != null) {
                            shopMapper.updateFans(shop.getFans() + 1, shop.getId());
                            resultSet = ResultSet.success(null, "订阅成功");
                        }
                    }
                }
            }
        }
        if (resultSet == null) {
            resultSet = ResultSet.error();
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }


    /**
     * 举报商品
     *
     * @param report
     * @return
     */
    @Override
    public ResultSet reportGoods(Report report) {
        ResultSet resultSet = null;
        if (reportMapper.selectByUAGId(report.getUserId(), report.getGoodsId()) == null) {
            synchronized (reportMapper) {
                if (reportMapper.selectByUAGId(report.getUserId(), report.getGoodsId()) == null) {
                    reportMapper.insert(report.getGoodsId(), report.getUserId(), 0, report.getDescription());
                    resultSet = ResultSet.success(null, "举报成功");
                }
            }
        }
        if (resultSet == null) {
            resultSet = ResultSet.error(null, "请勿重复举报");
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }

    /**
     * 查看关注店铺的推文
     *
     * @param id
     * @return
     */
    @Override
    public ResultSet selectTweets(Integer id) {
        List<Subscrible> subscribles = subscribleMapper.selectbyUserId(id);
        List<ShopTweets> tweets = new ArrayList<>();
        if (subscribles != null) {
            for (Subscrible subscrible : subscribles) {
                List<Tweets> tweets1 = tweetsMapper.selectByShopId(subscrible.getShopId());
                if (tweets1 != null) {
                    for (Tweets tweets2 : tweets1) {
                        tweets.add(new ShopTweets(tweets2.getId(), tweets2.getShopId(), shopMapper.selectById(tweets2.getShopId()).getName(), tweets2.getTweets()));
                    }
                }
            }
        }
        tweets.sort(new Comparator<ShopTweets>() {
            @Override
            public int compare(ShopTweets o1, ShopTweets o2) {
                return o1.getId() > o2.getId() ? 1 : -1;
            }
        });
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(tweets, null);
    }

    /**
     * 查找关注店铺
     *
     * @param id
     * @return
     */
    @Override
    public ResultSet selectSubscrible(Integer id) {
        List<Subscrible> subscribles = subscribleMapper.selectbyUserId(id);
        List<UserSubscrible> userSubscribles = new ArrayList<>();
        if (subscribles != null) {
            for (Subscrible subscrible : subscribles) {
                userSubscribles.add(new UserSubscrible(subscrible.getId(), subscrible.getShopId(), shopMapper.selectById(subscrible.getShopId()).getName()));
            }
        }
        SqlSessionUtil.close();
        return ResultSet.success(userSubscribles, "查询成功");
    }


    /**
     * 取关
     *
     * @param userSubscrible
     * @param id
     * @return
     */
    @Override
    public ResultSet unfollow(UserSubscrible userSubscrible, Integer id) {
        subscribleMapper.deleteByUASId(id, userSubscrible.getShopId());
        synchronized (shopMapper) {
            Shop shop = shopMapper.selectById(userSubscrible.getShopId());
            shopMapper.updateFans(shop.getFans() - 1, userSubscrible.getShopId());
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success();
    }


    /**
     * 查看个人信息
     *
     * @param id
     * @return
     */
    @Override
    public ResultSet selectPersonal(Integer id) {
        ResultSet resultSet = null;
        User user = userMapper.selectById(id);
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        if (user != null) {
            user.setPassword(null);
            user.setPhone(null);
            resultSet = ResultSet.success(user, null);
        } else {
            resultSet = ResultSet.error();
        }
        return resultSet;
    }


    /**
     * 修改个人信息
     *
     * @param user
     * @return
     */
    @Override
    public ResultSet updatePersonal(User user) {
        ResultSet resultSet = null;
        //判断敏感词
        for (String s : CONST.SENSITIVE) {
            if (user.getAddress().contains(s) || user.getUserName().contains(s)) {
                resultSet = ResultSet.error("userName", "用户名或地址含有敏感字");
            }
        }
        if (resultSet == null) {
            userMapper.updateMessage(user.getUserName(), user.getAddress(), user.getIsPrivate(), user.getId());
            SqlSessionUtil.commit();
            SqlSessionUtil.close();
            resultSet = ResultSet.success();
        }
        return resultSet;
    }

    /**
     * 查找用户信息
     * @param id
     * @return
     */
    @Override
    public ResultSet selectMessage(Integer id) {
        List<UserMessage> userMessages = userMessageMapper.selectByUserId(id);
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(userMessages,null);
    }

    /**
     * 删除用户信息
     * @param userMessage
     * @return
     */
    @Override
    public ResultSet deleteMessage(UserMessage userMessage) {
        ResultSet resultSet = null;
        if (userMessageMapper.deleteById(userMessage.getId()) == 1) {
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
