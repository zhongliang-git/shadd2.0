package com.aidex.shadd.consumer.service;

import com.aidex.shadd.consumer.domain.GoodsModel;
import com.github.pagehelper.PageInfo;

/**
 * @author zhongliang
 * @version 1.0
 * @description: 商品服务接口
 * @date 2022/4/8 13:33
 */
public interface IGoodsService {

    /**
     * 查询商品列表
     * @param: goodsModel 查询条件
     * @author zhongliang
     * @date: 2022/4/8 13:35
     */
    PageInfo<GoodsModel> findGoodsList(GoodsModel goodsModel);
}
