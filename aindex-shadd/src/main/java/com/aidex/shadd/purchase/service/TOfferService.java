package com.aidex.shadd.purchase.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.purchase.domain.TOffer;

/**
 * 厂家报价子表Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
public interface TOfferService extends BaseService<TOffer>
{

    /**
     * 批量删除厂家报价子表
     * @param ids 需要删除的厂家报价子表ID集合
     * @return 结果
     */
    public int deleteTOfferByIds(String[] ids);


}
