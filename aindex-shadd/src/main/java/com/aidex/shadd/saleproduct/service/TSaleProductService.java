package com.aidex.shadd.saleproduct.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.saleproduct.domain.TSaleProduct;
import com.github.pagehelper.PageInfo;

/**
 * 销售产品Service接口
 *
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
public interface TSaleProductService extends BaseService<TSaleProduct> {

    /**
     * 批量删除销售产品
     *
     * @param ids 需要删除的销售产品ID集合
     * @return 结果
     */
    int deleteTSaleProductByIds(String[] ids);

    boolean saveOrUpdate(TSaleProduct saleProduct);

    /**
     * 小程序端商品查询
     *
     * @param saleProduct 查询条件
     * @return 结果
     */
    PageInfo<TSaleProduct> findGoodsPage(TSaleProduct saleProduct);
}
