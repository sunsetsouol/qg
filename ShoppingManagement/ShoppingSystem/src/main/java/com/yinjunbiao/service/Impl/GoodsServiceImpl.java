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
            cartMapper.insert(cart.getGoodsId(),cart.getShopId(),cart.getNumber(), cart.getUserId(),cart.getSinglePrice());
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(null,"修改成功");
    }


}
