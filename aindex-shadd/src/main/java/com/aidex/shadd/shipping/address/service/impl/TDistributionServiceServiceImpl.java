package com.aidex.shadd.shipping.address.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.shadd.shipping.address.domain.TDistributionService;
import com.aidex.shadd.shipping.address.mapper.TDistributionServiceMapper;
import com.aidex.shadd.shipping.address.service.TDistributionServiceService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 配送服务子表Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
@Service
@Transactional(readOnly = true)
public class TDistributionServiceServiceImpl extends BaseServiceImpl<TDistributionServiceMapper, TDistributionService> implements TDistributionServiceService
{
    private static final Logger log = LoggerFactory.getLogger(TDistributionServiceServiceImpl.class);

    /**
     * 获取单条数据
     * @param tDistributionService 配送服务子表
     * @return 配送服务子表
     */
    @Override
    public TDistributionService get(TDistributionService tDistributionService)
    {
        TDistributionService dto = super.get(tDistributionService);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 配送服务子表id
     * @return 配送服务子表
     */
    @Override
    public TDistributionService get(String id)
    {
        TDistributionService dto = super.get(id);
        return dto;
    }


    /**
     * 查询配送服务子表列表
     * @param tDistributionService 配送服务子表
     * @return 配送服务子表
     */
    @Override
    public List<TDistributionService> findList(TDistributionService tDistributionService)
    {
        return super.findList(tDistributionService);
    }

    /**
     * 分页查询配送服务子表列表
     * @param tDistributionService 配送服务子表
     * @return 配送服务子表
     */
    @Override
    public PageInfo<TDistributionService> findPage(TDistributionService tDistributionService)
    {
        return super.findPage(tDistributionService);
    }

    /**
     * 保存配送服务子表
     * @param tDistributionService
     * @return 结果
     */
    @Override
    public boolean save(TDistributionService tDistributionService)
    {
        return super.save(tDistributionService);
    }


    /**
     * 删除配送服务子表信息
     * @param tDistributionService
     * @return 结果
     */
    @Override
    public boolean remove(TDistributionService tDistributionService)
    {
        return super.remove(tDistributionService);
    }

    /**
     * 批量删除配送服务子表
     * @param ids 需要删除的配送服务子表ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTDistributionServiceByIds(String[] ids)
    {
        return mapper.deleteTDistributionServiceByIds(ids);
    }

}
