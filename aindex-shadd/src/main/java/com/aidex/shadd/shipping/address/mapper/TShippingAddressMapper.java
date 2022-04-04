package com.aidex.shadd.shipping.address.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.shipping.address.domain.TShippingAddress;

/**
 * 收货地址Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
public interface TShippingAddressMapper extends BaseMapper<TShippingAddress>
{

    /**
     * 批量删除收货地址
     * @param ids 需要删除的收货地址ID集合
     * @return
     */
    public int deleteTShippingAddressByIds(String[] ids);


}
