package com.aidex.shadd.repertory.service.impl;

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
import com.aidex.shadd.repertory.mapper.TRepertoryMapper;
import com.aidex.shadd.repertory.domain.TRepertory;
import com.aidex.shadd.repertory.service.TRepertoryService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 库存Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
@Service
@Transactional(readOnly = true)
public class TRepertoryServiceImpl extends BaseServiceImpl<TRepertoryMapper, TRepertory> implements TRepertoryService
{
    private static final Logger log = LoggerFactory.getLogger(TRepertoryServiceImpl.class);

    /**
     * 获取单条数据
     * @param tRepertory 库存
     * @return 库存
     */
    @Override
    public TRepertory get(TRepertory tRepertory)
    {
        TRepertory dto = super.get(tRepertory);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 库存id
     * @return 库存
     */
    @Override
    public TRepertory get(String id)
    {
        TRepertory dto = super.get(id);
        return dto;
    }


    /**
     * 查询库存列表
     * @param tRepertory 库存
     * @return 库存
     */
    @Override
    public List<TRepertory> findList(TRepertory tRepertory)
    {
        return super.findList(tRepertory);
    }

    /**
     * 分页查询库存列表
     * @param tRepertory 库存
     * @return 库存
     */
    @Override
    public PageInfo<TRepertory> findPage(TRepertory tRepertory)
    {
        return super.findPage(tRepertory);
    }

    /**
     * 保存库存
     * @param tRepertory
     * @return 结果
     */
    @Override
    public boolean save(TRepertory tRepertory)
    {
        return super.save(tRepertory);
    }


    /**
     * 删除库存信息
     * @param tRepertory
     * @return 结果
     */
    @Override
    public boolean remove(TRepertory tRepertory)
    {
        return super.remove(tRepertory);
    }

    /**
     * 批量删除库存
     * @param ids 需要删除的库存ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTRepertoryByIds(String[] ids)
    {
        return mapper.deleteTRepertoryByIds(ids);
    }

}
