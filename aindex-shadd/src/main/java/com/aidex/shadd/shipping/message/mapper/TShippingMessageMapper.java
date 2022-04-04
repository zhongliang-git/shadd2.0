package com.aidex.shadd.shipping.message.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.shipping.message.domain.TShippingMessage;

/**
 * 收货信息Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
public interface TShippingMessageMapper extends BaseMapper<TShippingMessage>
{

    /**
     * 批量删除收货信息
     * @param ids 需要删除的收货信息ID集合
     * @return
     */
    public int deleteTShippingMessageByIds(String[] ids);


}
