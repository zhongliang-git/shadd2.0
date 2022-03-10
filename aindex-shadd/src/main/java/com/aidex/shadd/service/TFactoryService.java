package com.aidex.shadd.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.domain.TFactory;

/**
 * 厂家Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-08
 */
public interface TFactoryService extends BaseService<TFactory>
{

    /**
     * 更新厂家状态
     * @param tFactory
     * @return 结果
     */
    public int updateStatus(TFactory tFactory);

    /**
     * 批量删除厂家
     * @param ids 需要删除的厂家ID集合
     * @return 结果
     */
    public int deleteTFactoryByIds(String[] ids);


}
