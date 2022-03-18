package com.aidex.shadd.product.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.product.domain.TProduct;

import java.util.List;

/**
 * 产品Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-10
 */
public interface TProductService extends BaseService<TProduct>
{

    /**
     * 批量删除产品
     * @param ids 需要删除的产品ID集合
     * @return 结果
     */
     int deleteTProductByIds(String[] ids);

    /**
     * 模糊查询-矿种/属性规格
     * @param product 查询条件
     * @return 结果
     */
     List<TProduct> queryListOrBy(TProduct product);
}
