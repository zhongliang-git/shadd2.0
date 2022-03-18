package com.aidex.shadd.product.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.product.domain.TProduct;

import java.util.List;

/**
 * 产品Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-10
 */
public interface TProductMapper extends BaseMapper<TProduct>
{

    /**
     * 批量删除产品
     * @param ids 需要删除的产品ID集合
     * @return
     */
     int deleteTProductByIds(String[] ids);

    /**
     * 模糊查询-矿种/属性规格
     * @param product 查询条件
     * @return 结果
     */
     List<TProduct> queryListOrBy(TProduct product);

    /**
     * 根据Id查询
     * @param id 查询条件
     * @return 结果
     */
     TProduct queryTProductById(String id);
}
