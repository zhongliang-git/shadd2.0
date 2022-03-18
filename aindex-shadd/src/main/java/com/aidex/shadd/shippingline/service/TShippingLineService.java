package com.aidex.shadd.shippingline.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.shippingline.domain.TShippingLine;

/**
 * 运线Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-16
 */
public interface TShippingLineService extends BaseService<TShippingLine>
{

    /**
     * 批量删除运线
     * @param ids 需要删除的运线ID集合
     * @return 结果
     */
    int deleteTShippingLineByIds(String[] ids);
}
