package com.aidex.shadd.purchase.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.purchase.domain.TPurchase;
import com.github.pagehelper.PageInfo;

/**
 * 采购产品Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
public interface TPurchaseService extends BaseService<TPurchase>
{

    /**
     * 批量删除采购产品
     * @param ids 需要删除的采购产品ID集合
     * @return 结果
     */
    public int deleteTPurchaseByIds(String[] ids);

    /**
     * 批量删除采购产品 根据厂家Id
     * @param factoryId 厂家Id
     * @return 结果
     */
    void removeByFactoryId(String factoryId);

    /**
     * 查询采购产品（仅返回产品名称，产品id)
     * @param tPurchase 查询条件（仅支持，产品名称+厂家） 模糊查询
     * @return 结果
     */
    PageInfo<TPurchase> findPurchaseListPage(TPurchase tPurchase);
}
