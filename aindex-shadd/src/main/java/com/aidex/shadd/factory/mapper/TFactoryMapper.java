package com.aidex.shadd.factory.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.factory.domain.TFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 厂家Mapper接口
 *
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-08
 */
@Repository
public interface TFactoryMapper extends BaseMapper<TFactory> {
    /**
     * 更新厂家状态
     *
     * @param tFactory 厂家
     * @return 结果
     */
    int updateStatus(TFactory tFactory);

    /**
     * 批量删除厂家
     *
     * @param ids 需要删除的厂家ID集合
     * @return
     */
    int deleteTFactoryByIds(String[] ids);

    /**
     * 查询厂家根据Id
     *
     * @param id 厂家ID
     * @return
     */
    TFactory queryTFactoryById(String id);

    /**
     * 查询厂家根据厂家名称
     *
     * @param tFactory 厂家对象
     * @return
     */
    List<TFactory> findListByName(TFactory tFactory);
}
