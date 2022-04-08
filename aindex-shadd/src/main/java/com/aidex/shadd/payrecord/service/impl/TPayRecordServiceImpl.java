package com.aidex.shadd.payrecord.service.impl;

import com.aidex.common.core.domain.entity.SysUser;
import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.framework.cache.UserUtils;
import com.aidex.shadd.payrecord.domain.TPayRecord;
import com.aidex.shadd.payrecord.mapper.TPayRecordMapper;
import com.aidex.shadd.payrecord.service.TPayRecordService;
import com.aidex.shadd.userInfo.service.UserInfoService;
import com.aidex.shadd.util.CodeUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 充值记录Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
@Service
@Transactional(readOnly = true)
public class TPayRecordServiceImpl extends BaseServiceImpl<TPayRecordMapper, TPayRecord> implements TPayRecordService
{
    private static final Logger log = LoggerFactory.getLogger(TPayRecordServiceImpl.class);
    @Autowired
    private UserInfoService userInfoService;
    /**
     * 获取单条数据
     * @param tPayRecord 充值记录
     * @return 充值记录
     */
    @Override
    public TPayRecord get(TPayRecord tPayRecord)
    {
        TPayRecord dto = super.get(tPayRecord);
                SysUser sysUser = UserUtils.getSysUser(dto.getConsumerId());
            dto.setConsumerIdName(sysUser.getName());
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 充值记录id
     * @return 充值记录
     */
    @Override
    public TPayRecord get(String id)
    {
        TPayRecord dto = super.get(id);
                SysUser sysUser = UserUtils.getSysUser(dto.getConsumerId());
                dto.setConsumerIdName(sysUser.getName());
        return dto;
    }


    /**
     * 查询充值记录列表
     * @param tPayRecord 充值记录
     * @return 充值记录
     */
    @Override
    public List<TPayRecord> findList(TPayRecord tPayRecord)
    {
        return super.findList(tPayRecord);
    }

    /**
     * 分页查询充值记录列表
     * @param tPayRecord 充值记录
     * @return 充值记录
     */
    @Override
    public PageInfo<TPayRecord> findPage(TPayRecord tPayRecord)
    {
        return super.findPage(tPayRecord);
    }

    /**
     * 保存充值记录
     * @param tPayRecord
     * @return 结果
     */
    @Override
    public boolean save(TPayRecord tPayRecord)
    {
        return super.save(tPayRecord);
    }


    /**
     * 删除充值记录信息
     * @param tPayRecord
     * @return 结果
     */
    @Override
    public boolean remove(TPayRecord tPayRecord)
    {
        return super.remove(tPayRecord);
    }

    /**
     * 批量删除充值记录
     * @param ids 需要删除的充值记录ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTPayRecordByIds(String[] ids)
    {
        return mapper.deleteTPayRecordByIds(ids);
    }

    @Override
    public boolean saveAndUpdateBalance(TPayRecord tPayRecord) {
        tPayRecord.setCode(CodeUtil.generatePayCode());
        boolean isSucceed = super.save(tPayRecord);
        if (!isSucceed) {
            return false;
        }
        BigDecimal payMoney = tPayRecord.getPayMoney();
        BigDecimal donateMoney = tPayRecord.getDonateMoney();
        if (donateMoney != null) {
            payMoney = payMoney.add(donateMoney);
        }
        return userInfoService.addBalance(tPayRecord.getConsumerId(), payMoney);
    }

}
