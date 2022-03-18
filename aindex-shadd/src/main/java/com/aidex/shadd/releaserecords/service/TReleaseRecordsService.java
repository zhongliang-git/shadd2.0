package com.aidex.shadd.releaserecords.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.releaserecords.domain.TReleaseRecords;

/**
 * 发货记录Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-16
 */
public interface TReleaseRecordsService extends BaseService<TReleaseRecords>
{

    /**
     * 批量删除发货记录
     * @param ids 需要删除的发货记录ID集合
     * @return 结果
     */
    public int deleteTReleaseRecordsByIds(String[] ids);


}
