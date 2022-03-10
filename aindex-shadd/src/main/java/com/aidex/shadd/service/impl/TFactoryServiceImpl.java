package com.aidex.shadd.service.impl;

import java.util.List;

import com.aidex.shadd.service.TFactoryService;
import com.github.pagehelper.PageInfo;
import com.aidex.common.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aidex.shadd.mapper.TFactoryMapper;
import com.aidex.shadd.domain.TFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * 厂家Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-08
 */
@Service
@Transactional(readOnly = true)
public class TFactoryServiceImpl extends BaseServiceImpl<TFactoryMapper, TFactory> implements TFactoryService
{
    private static final Logger log = LoggerFactory.getLogger(TFactoryServiceImpl.class);

    /**
     * 获取单条数据
     * @param tFactory 厂家
     * @return 厂家
     */
    @Override
    public TFactory get(TFactory tFactory)
    {
        TFactory dto = super.get(tFactory);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 厂家id
     * @return 厂家
     */
    @Override
    public TFactory get(String id)
    {
        TFactory dto = super.get(id);
        return dto;
    }


    /**
     * 查询厂家列表
     * @param tFactory 厂家
     * @return 厂家
     */
    @Override
    public List<TFactory> findList(TFactory tFactory)
    {
        return super.findList(tFactory);
    }

    /**
     * 分页查询厂家列表
     * @param tFactory 厂家
     * @return 厂家
     */
    @Override
    public PageInfo<TFactory> findPage(TFactory tFactory)
    {
        return super.findPage(tFactory);
    }

    /**
     * 保存厂家
     * @param tFactory
     * @return 结果
     */
    @Override
    public boolean save(TFactory tFactory)
    {
        return super.save(tFactory);
    }

    /**
     * 更新状态厂家
     * @param tFactory
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int updateStatus(TFactory tFactory)
    {
        return mapper.updateStatus(tFactory);
    }

    /**
     * 删除厂家信息
     * @param tFactory
     * @return 结果
     */
    @Override
    public boolean remove(TFactory tFactory)
    {
        return super.remove(tFactory);
    }

    /**
     * 批量删除厂家
     * @param ids 需要删除的厂家ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTFactoryByIds(String[] ids)
    {
        return mapper.deleteTFactoryByIds(ids);
    }

}
