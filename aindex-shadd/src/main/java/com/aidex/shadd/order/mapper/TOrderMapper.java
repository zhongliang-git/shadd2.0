package com.aidex.shadd.order.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.order.domain.TOrder;

/**
 * 订单Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-07
 */
public interface TOrderMapper extends BaseMapper<TOrder>
{

    /**
     * 批量删除订单
     * @param ids 需要删除的订单ID集合
     * @return
     */
    public int deleteTOrderByIds(String[] ids);


}
