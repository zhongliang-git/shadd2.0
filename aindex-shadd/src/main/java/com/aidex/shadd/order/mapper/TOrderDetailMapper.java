package com.aidex.shadd.order.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.order.domain.TOrderDetail;

/**
 * 订单明细子表Mapper接口
 * @author ${functionAuthor}
 * @email ${functionAuthorEmail}
 * @date 2022-04-07
 */
public interface TOrderDetailMapper extends BaseMapper<TOrderDetail>
{

    /**
     * 批量删除订单明细子表
     * @param ids 需要删除的订单明细子表ID集合
     * @return
     */
    public int deleteTOrderDetailByIds(String[] ids);


}
