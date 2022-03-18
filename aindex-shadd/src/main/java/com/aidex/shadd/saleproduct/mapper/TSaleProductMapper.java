package com.aidex.shadd.saleproduct.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.saleproduct.domain.TSaleProduct;

/**
 * 销售产品Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
public interface TSaleProductMapper extends BaseMapper<TSaleProduct>
{

    /**
     * 批量删除销售产品
     * @param ids 需要删除的销售产品ID集合
     * @return
     */
    public int deleteTSaleProductByIds(String[] ids);


}
