package com.aidex.shadd.releaserecords.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.releaserecords.domain.TReleaseRecords;

/**
 * 发货记录Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-16
 */
public interface TReleaseRecordsMapper extends BaseMapper<TReleaseRecords>
{

    /**
     * 批量删除发货记录
     * @param ids 需要删除的发货记录ID集合
     * @return
     */
    public int deleteTReleaseRecordsByIds(String[] ids);


}
