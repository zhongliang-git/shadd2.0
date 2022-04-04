package com.aidex.shadd.shipping.address.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.shipping.address.domain.TShippingAddress;

/**
 * 收货地址Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
public interface TShippingAddressService extends BaseService<TShippingAddress>
{

    /**
     * 批量删除收货地址
     * @param ids 需要删除的收货地址ID集合
     * @return 结果
     */
    public int deleteTShippingAddressByIds(String[] ids);


}
