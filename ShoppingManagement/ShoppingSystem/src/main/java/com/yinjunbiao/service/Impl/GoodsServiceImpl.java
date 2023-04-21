package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.Cart;
import com.yinjunbiao.entity.Goods;
import com.yinjunbiao.entity.Orders;
import com.yinjunbiao.mapper.*;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.GoodsService;
import com.yinjunbiao.util.SqlSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component("goodsService")
@Scope("singleton")
public class GoodsServiceImpl implements GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger("GoodsServiceImpl.class");

    public GoodsServiceImpl() {
    }

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public ResultSet selectByPage(Integer currentPage, Integer pageSize) {
        List<Goods> goods = goodsMapper.selectAll(currentPage-1, pageSize);
        System.out.println(goods);
        return ResultSet.success(goods,"查找成功");
    }

    @Override
    public ResultSet selectByName(String name,Integer currentPage, Integer pageSize) {
        name = "%" + name + "%";
        List<Goods> goods = goodsMapper.selectByName(name,currentPage-1,pageSize);
        System.out.println(goods);
        return  ResultSet.success(goods,"查询成功");
    }

    /**
     * 生成订单
     * @param orders
     * @return
     */
    @Override
    public ResultSet newOrders(Orders orders) {
        ResultSet resultSet = null;
        Goods goods = goodsMapper.selectById(orders.getGoodsId());
        if (orders.getNumber() <= goods.getInventory()){
            synchronized (goodsMapper){
                goods = goodsMapper.selectById(orders.getGoodsId());
                if (orders.getNumber() <= goods.getInventory()){
                    //插入订单同时库存减
                    ordersMapper.insert(System.currentTimeMillis(),userMapper.selectById(shopMapper.selectById(goods.getShopId()).getBossId()).getAddress(),userMapper.selectById(orders.getUserId()).getAddress(),orders.getGoodsId(),orders.getUserId(),orders.getNumber(),orders.getSinglePrice());
                    goodsMapper.updateInventory(goods.getInventory() - orders.getNumber(),orders.getGoodsId());
                    resultSet = ResultSet.success();
                }
            }
        }
        if (resultSet == null){
            SqlSessionUtil.rollback();
            resultSet = ResultSet.error(null,"库存不足");
        }else {
            SqlSessionUtil.commit();
        }
        SqlSessionUtil.close();
        return resultSet;
    }

    /**
     * 修改购物车数量
     * @param cart
     * @return
     */
    @Override
    public ResultSet changeShoppingCart(Cart cart) {
        Cart select = cartMapper.selectByUAGId(cart.getUserId(), cart.getGoodsId());
        if (select != null){
            Long id = select.getId();
            if (cart.getNumber() == 0){
                cartMapper.deleteById(id);
            }else {
                cartMapper.updateNumber(cart.getNumber(),id);
            }
        }else {
            cartMapper.insert(cart.getGoodsId(),cart.getNumber(), cart.getUserId(),cart.getSinglePrice());
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(null,"修改成功");
    }

    /**
     * 添加到购物车
     */
    @Override
    public ResultSet addCart(Cart cart) {
        Cart shoppingCart = cartMapper.selectByUAGId(cart.getUserId(), cart.getGoodsId());
        ResultSet resultSet = null;
        if (shoppingCart == null){
            synchronized (cartMapper){
                shoppingCart = cartMapper.selectByUAGId(cart.getUserId(),cart.getGoodsId());
                if (shoppingCart == null){
                    if (cartMapper.insert(cart.getGoodsId(),cart.getNumber(),cart.getUserId(),cart.getSinglePrice()) == 1) {
                        resultSet = ResultSet.success(null,"插入成功");
                    }
                }
            }
        }
        if (resultSet == null) {
            cart.setNumber(shoppingCart.getNumber() + cart.getNumber());
            resultSet = changeShoppingCart(cart);
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return resultSet;
    }
}
