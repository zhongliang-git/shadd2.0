package com.aidex.shadd.consumer.mapper;

import com.aidex.shadd.consumer.domain.GoodsModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhongliang
 * @version 1.0
 * @description: 商品Mapper
 * @date 2022/4/8 13:27
 */
@Repository
public interface IGoodsMapper {

    /**
     *  查询商品列表
     * @param: addressId 收货地址
     * @author zhongliang
     * @date: 2022/4/8 13:29
     */
    List<GoodsModel> queryGoodsList(@Param("d") GoodsModel goodsModel);
}
