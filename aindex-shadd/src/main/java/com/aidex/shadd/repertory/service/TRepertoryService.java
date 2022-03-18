package com.aidex.shadd.repertory.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.repertory.domain.TRepertory;

/**
 * 库存Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
public interface TRepertoryService extends BaseService<TRepertory>
{

    /**
     * 批量删除库存
     * @param ids 需要删除的库存ID集合
     * @return 结果
     */
    public int deleteTRepertoryByIds(String[] ids);


}
