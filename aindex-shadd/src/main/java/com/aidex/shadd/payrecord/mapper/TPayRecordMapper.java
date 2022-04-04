package com.aidex.shadd.payrecord.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.payrecord.domain.TPayRecord;

/**
 * 充值记录Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
public interface TPayRecordMapper extends BaseMapper<TPayRecord>
{

    /**
     * 批量删除充值记录
     * @param ids 需要删除的充值记录ID集合
     * @return
     */
    public int deleteTPayRecordByIds(String[] ids);


}
