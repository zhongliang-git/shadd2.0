package com.aidex.shadd.shipping.message.service.impl;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.aidex.common.core.domain.entity.SysUser;
import com.aidex.common.core.domain.entity.SysDept;
import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.framework.cache.DeptUtils;
import com.aidex.framework.cache.UserUtils;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aidex.shadd.shipping.message.mapper.TShippingMessageMapper;
import com.aidex.shadd.shipping.message.domain.TShippingMessage;
import com.aidex.shadd.shipping.message.service.TShippingMessageService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 收货信息Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
@Service
@Transactional(readOnly = true)
public class TShippingMessageServiceImpl extends BaseServiceImpl<TShippingMessageMapper, TShippingMessage> implements TShippingMessageService
{
    private static final Logger log = LoggerFactory.getLogger(TShippingMessageServiceImpl.class);

    /**
     * 获取单条数据
     * @param tShippingMessage 收货信息
     * @return 收货信息
     */
    @Override
    public TShippingMessage get(TShippingMessage tShippingMessage)
    {
        TShippingMessage dto = super.get(tShippingMessage);
                SysUser sysUser = UserUtils.getSysUser(dto.getUserid());
            dto.setUseridName(sysUser.getName());
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 收货信息id
     * @return 收货信息
     */
    @Override
    public TShippingMessage get(String id)
    {
        TShippingMessage dto = super.get(id);
                SysUser sysUser = UserUtils.getSysUser(dto.getUserid());
                dto.setUseridName(sysUser.getName());
        return dto;
    }


    /**
     * 查询收货信息列表
     * @param tShippingMessage 收货信息
     * @return 收货信息
     */
    @Override
    public List<TShippingMessage> findList(TShippingMessage tShippingMessage)
    {
        return super.findList(tShippingMessage);
    }

    /**
     * 分页查询收货信息列表
     * @param tShippingMessage 收货信息
     * @return 收货信息
     */
    @Override
    public PageInfo<TShippingMessage> findPage(TShippingMessage tShippingMessage)
    {
        return super.findPage(tShippingMessage);
    }

    /**
     * 保存收货信息
     * @param tShippingMessage
     * @return 结果
     */
    @Override
    public boolean save(TShippingMessage tShippingMessage)
    {
        return super.save(tShippingMessage);
    }


    /**
     * 删除收货信息信息
     * @param tShippingMessage
     * @return 结果
     */
    @Override
    public boolean remove(TShippingMessage tShippingMessage)
    {
        return super.remove(tShippingMessage);
    }

    /**
     * 批量删除收货信息
     * @param ids 需要删除的收货信息ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTShippingMessageByIds(String[] ids)
    {
        return mapper.deleteTShippingMessageByIds(ids);
    }

}
