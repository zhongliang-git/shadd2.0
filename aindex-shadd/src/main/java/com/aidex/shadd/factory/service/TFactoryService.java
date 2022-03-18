package com.aidex.shadd.factory.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.factory.domain.TFactory;
import com.github.pagehelper.PageInfo;

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
     int updateStatus(TFactory tFactory);

    /**
     * 批量删除厂家
     * @param ids 需要删除的厂家ID集合
     * @return 结果
     */
    int deleteTFactoryByIds(String[] ids);

    /**
     * 根据Id查询厂家（包含子表信息）
     * @param id 厂家ID
     * @return 结果
     */
    TFactory findTFactoryById(String id);

    /**
     * 修改厂家信息
     * @param tFactory 厂家
     * @return 结果
     */
    TFactory modifyTFactory(TFactory tFactory);

    /**
     * 查询厂家信息（仅返回厂家名称、id)
     * @param tFactory 厂家
     * @return 结果
     */
    PageInfo<TFactory> findListByName(TFactory tFactory);
}
