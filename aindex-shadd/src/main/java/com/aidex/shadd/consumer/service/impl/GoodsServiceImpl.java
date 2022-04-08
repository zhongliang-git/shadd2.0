package com.aidex.shadd.consumer.service.impl;

import com.aidex.shadd.consumer.domain.GoodsModel;
import com.aidex.shadd.consumer.mapper.IGoodsMapper;
import com.aidex.shadd.consumer.service.IGoodsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhongliang
 * @version 1.0
 * @description: 商品服务接口实现
 * @date 2022/4/8 13:36
 */
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private IGoodsMapper goodsMapper;

    @Override
    public PageInfo<GoodsModel> findGoodsList(GoodsModel goodsModel) {
        goodsMapper.queryGoodsList(goodsModel);
        return null;
    }
}
