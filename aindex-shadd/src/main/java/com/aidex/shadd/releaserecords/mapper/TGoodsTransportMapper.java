package com.aidex.shadd.releaserecords.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.releaserecords.domain.TGoodsTransport;

/**
 * 货物运输子表Mapper接口
 * @author ${functionAuthor}
 * @email ${functionAuthorEmail}
 * @date 2022-03-16
 */
public interface TGoodsTransportMapper extends BaseMapper<TGoodsTransport>
{

    /**
     * 批量删除货物运输子表
     * @param ids 需要删除的货物运输子表ID集合
     * @return
     */
    public int deleteTGoodsTransportByIds(String[] ids);


}
