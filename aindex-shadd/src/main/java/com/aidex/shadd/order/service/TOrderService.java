package com.aidex.shadd.order.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.order.domain.TOrder;

/**
 * 订单Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-07
 */
public interface TOrderService extends BaseService<TOrder>
{

    /**
     * 批量删除订单
     * @param ids 需要删除的订单ID集合
     * @return 结果
     */
     int deleteTOrderByIds(String[] ids);

    /**
     * 用户下单
     * @param: order 订单信息
     * @author zhongliang
     * @date: 2022/4/7 15:29
     */
    void other(TOrder order);
}
