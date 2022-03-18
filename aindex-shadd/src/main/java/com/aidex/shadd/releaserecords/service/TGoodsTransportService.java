package com.aidex.shadd.releaserecords.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.releaserecords.domain.TGoodsTransport;

/**
 * 货物运输子表Service接口
 * @author ${functionAuthor}
 * @email ${functionAuthorEmail}
 * @date 2022-03-16
 */
public interface TGoodsTransportService extends BaseService<TGoodsTransport>
{

    /**
     * 批量删除货物运输子表
     * @param ids 需要删除的货物运输子表ID集合
     * @return 结果
     */
    public int deleteTGoodsTransportByIds(String[] ids);


}
