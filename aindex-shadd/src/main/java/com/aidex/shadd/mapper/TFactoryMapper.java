package com.aidex.shadd.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.domain.TFactory;

/**
 * 厂家Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-08
 */
public interface TFactoryMapper extends BaseMapper<TFactory>
{
    /**
     * 更新厂家状态
     * @param tFactory 厂家
     * @return 结果
     */
    public int updateStatus(TFactory tFactory);

    /**
     * 批量删除厂家
     * @param ids 需要删除的厂家ID集合
     * @return
     */
    public int deleteTFactoryByIds(String[] ids);


}
