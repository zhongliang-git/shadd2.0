package com.aidex.shadd.purchase.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.purchase.domain.TOffer;

import java.util.List;

/**
 * 厂家报价子表Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
public interface TOfferMapper extends BaseMapper<TOffer>
{

    /**
     * 批量删除厂家报价子表
     * @param ids 需要删除的厂家报价子表ID集合
     * @return
     */
     int deleteTOfferByIds(String[] ids);

    List<TOffer> queryTOfferByPurchaseId(String purchaseId);

    void deleteByPurchaseId(String deleteByPurchaseId);
}
