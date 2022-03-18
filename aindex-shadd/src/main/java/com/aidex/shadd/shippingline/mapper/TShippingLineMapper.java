package com.aidex.shadd.shippingline.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.shippingline.domain.TShippingLine;

/**
 * 运线Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-16
 */
public interface TShippingLineMapper extends BaseMapper<TShippingLine>
{

    /**
     * 批量删除运线
     * @param ids 需要删除的运线ID集合
     * @return
     */
    int deleteTShippingLineByIds(String[] ids);

}
