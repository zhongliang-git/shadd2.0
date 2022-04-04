package com.aidex.shadd.shipping.message.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.shipping.message.domain.TShippingMessage;

/**
 * 收货信息Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
public interface TShippingMessageService extends BaseService<TShippingMessage>
{

    /**
     * 批量删除收货信息
     * @param ids 需要删除的收货信息ID集合
     * @return 结果
     */
    public int deleteTShippingMessageByIds(String[] ids);


}
