package com.aidex.shadd.payrecord.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.payrecord.domain.TPayRecord;
import org.springframework.transaction.annotation.Transactional;

/**
 * 充值记录Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
public interface TPayRecordService extends BaseService<TPayRecord>
{

    /**
     * 批量删除充值记录
     * @param ids 需要删除的充值记录ID集合
     * @return 结果
     */
    int deleteTPayRecordByIds(String[] ids);


    @Transactional(readOnly = false)
    boolean saveAndUpdateBalance(TPayRecord tPayRecord);
}
