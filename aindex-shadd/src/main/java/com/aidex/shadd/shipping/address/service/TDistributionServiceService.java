package com.aidex.shadd.shipping.address.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.shipping.address.domain.TDistributionService;

/**
 * 配送服务子表Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
public interface TDistributionServiceService extends BaseService<TDistributionService>
{

    /**
     * 批量删除配送服务子表
     * @param ids 需要删除的配送服务子表ID集合
     * @return 结果
     */
    public int deleteTDistributionServiceByIds(String[] ids);


}
