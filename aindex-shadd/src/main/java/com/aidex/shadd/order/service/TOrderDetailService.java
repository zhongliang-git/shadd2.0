package com.aidex.shadd.order.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.order.domain.TOrderDetail;

import java.util.List;

/**
 * 订单明细子表Service接口
 * @author ${functionAuthor}
 * @email ${functionAuthorEmail}
 * @date 2022-04-07
 */
public interface TOrderDetailService extends BaseService<TOrderDetail>
{

    /**
     * 批量删除订单明细子表
     * @param ids 需要删除的订单明细子表ID集合
     * @return 结果
     */
    public int deleteTOrderDetailByIds(String[] ids);

    /**
     * 生成订单明细
     * @param: orderDetailList 订单明细数据
     * @author zhongliang
     * @date: 2022/4/7 15:36
     */
    void generateOrderDetail(List<TOrderDetail> orderDetailList);

    /**
     * 查询订单详情（根据用户Id+订单ID）
     * @param orderId 订单Id
     * @author zhongliang
     * @date: 2022/4/7 19:48
     */
    List<TOrderDetail> findOrderDetail(String orderId);
}
