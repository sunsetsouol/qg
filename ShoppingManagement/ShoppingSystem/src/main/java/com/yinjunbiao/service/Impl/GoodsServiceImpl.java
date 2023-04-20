package com.yinjunbiao.service.Impl;

import com.yinjunbiao.MySpring.Annotation.Autowired;
import com.yinjunbiao.MySpring.Annotation.Component;
import com.yinjunbiao.MySpring.Annotation.Scope;
import com.yinjunbiao.entity.Goods;
import com.yinjunbiao.mapper.GoodsMapper;
import com.yinjunbiao.pojo.ResultSet;
import com.yinjunbiao.service.GoodsService;
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
}
