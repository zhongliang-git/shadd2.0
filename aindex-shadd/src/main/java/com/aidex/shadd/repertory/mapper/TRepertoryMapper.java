package com.aidex.shadd.repertory.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.repertory.domain.TRepertory;

/**
 * 库存Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
public interface TRepertoryMapper extends BaseMapper<TRepertory>
{

    /**
     * 批量删除库存
     * @param ids 需要删除的库存ID集合
     * @return
     */
    public int deleteTRepertoryByIds(String[] ids);


}
