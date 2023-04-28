package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.*;
import com.yinjunbiao.mapper.*;
import com.yinjunbiao.pojo.GoodsConsultations;
import com.yinjunbiao.pojo.GoodsReply;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.GoodsService;
import com.yinjunbiao.util.SqlSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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

    @Autowired
    private ConsultationMapper consultationMapper;

    @Autowired
    private ReplyMapper replyMapper;


    @Override
    public ResultSet selectByPage(Integer currentPage, Integer pageSize) {
        List<Goods> goods = goodsMapper.selectAll((currentPage-1)*pageSize, pageSize);
        SqlSessionUtil.close();
        return ResultSet.success(goods,"查找成功");
    }

    @Override
    public ResultSet selectByName(String name,Integer currentPage, Integer pageSize) {
        name = "%" + name + "%";
        List<Goods> goods = goodsMapper.selectByName(name,(currentPage-1)*pageSize,pageSize);
        SqlSessionUtil.close();
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

    /**
     * 查找商品评价
     * @param id
     * @return
     */
    @Override
    public ResultSet selectConsultation(Long id) {
        List<Consultation> consultations = consultationMapper.selectByGoodsId(id);
        List<GoodsConsultations> goodsConsultations = new ArrayList<>();
        if (consultations != null){
            for (Consultation consultation : consultations) {
                goodsConsultations.add(new GoodsConsultations(consultation.getId(),userMapper.selectById(consultation.getUserId()).getUserName(),consultation.getConsultation()));
            }
        }
        SqlSessionUtil.close();
        return ResultSet.success(goodsConsultations,null);
    }

    /**
     * 发送评论
     * @param consultation
     * @return
     */
    @Override
    public ResultSet sendConsultation(Consultation consultation) {
        consultationMapper.insert(consultation.getGoodsId(), consultation.getConsultation(), consultation.getUserId());
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success();
    }

    /**
     * 发送回复
     * @param reply
     * @return
     */
    @Override
    public ResultSet sendReply(Reply reply) {
        replyMapper.insert(reply.getConsultationId(),reply.getReply(),reply.getUserId());
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success();
    }

    /**
     * 查询回复
     * @param id
     * @return
     */
    @Override
    public ResultSet selectReply(Long id) {
        List<Reply> replies = replyMapper.selectByCId(id);
        List<GoodsReply> goodsReplies = new ArrayList<>();
        if (replies != null){
            for (Reply reply : replies) {
                goodsReplies.add(new GoodsReply(reply.getId(),userMapper.selectById(reply.getUserId()).getUserName(),reply.getReply(),reply.getConsultationId()));
            }
        }
        SqlSessionUtil.commit();
        SqlSessionUtil.close();
        return ResultSet.success(goodsReplies,null);
    }




}
