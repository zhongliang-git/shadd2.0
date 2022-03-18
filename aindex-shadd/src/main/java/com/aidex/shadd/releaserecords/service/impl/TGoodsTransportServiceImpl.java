package com.aidex.shadd.releaserecords.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.shadd.releaserecords.domain.TGoodsTransport;
import com.aidex.shadd.releaserecords.mapper.TGoodsTransportMapper;
import com.aidex.shadd.releaserecords.service.TGoodsTransportService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 货物运输子表Service业务层处理
 * @author ${functionAuthor}
 * @email ${functionAuthorEmail}
 * @date 2022-03-16
 */
@Service
@Transactional(readOnly = true)
public class TGoodsTransportServiceImpl extends BaseServiceImpl<TGoodsTransportMapper, TGoodsTransport> implements TGoodsTransportService
{
    private static final Logger log = LoggerFactory.getLogger(TGoodsTransportServiceImpl.class);

    /**
     * 获取单条数据
     * @param tGoodsTransport 货物运输子表
     * @return 货物运输子表
     */
    @Override
    public TGoodsTransport get(TGoodsTransport tGoodsTransport)
    {
        TGoodsTransport dto = super.get(tGoodsTransport);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 货物运输子表id
     * @return 货物运输子表
     */
    @Override
    public TGoodsTransport get(String id)
    {
        TGoodsTransport dto = super.get(id);
        return dto;
    }


    /**
     * 查询货物运输子表列表
     * @param tGoodsTransport 货物运输子表
     * @return 货物运输子表
     */
    @Override
    public List<TGoodsTransport> findList(TGoodsTransport tGoodsTransport)
    {
        return super.findList(tGoodsTransport);
    }

    /**
     * 分页查询货物运输子表列表
     * @param tGoodsTransport 货物运输子表
     * @return 货物运输子表
     */
    @Override
    public PageInfo<TGoodsTransport> findPage(TGoodsTransport tGoodsTransport)
    {
        return super.findPage(tGoodsTransport);
    }

    /**
     * 保存货物运输子表
     * @param tGoodsTransport
     * @return 结果
     */
    @Override
    public boolean save(TGoodsTransport tGoodsTransport)
    {
        return super.save(tGoodsTransport);
    }


    /**
     * 删除货物运输子表信息
     * @param tGoodsTransport
     * @return 结果
     */
    @Override
    public boolean remove(TGoodsTransport tGoodsTransport)
    {
        return super.remove(tGoodsTransport);
    }

    /**
     * 批量删除货物运输子表
     * @param ids 需要删除的货物运输子表ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTGoodsTransportByIds(String[] ids)
    {
        return mapper.deleteTGoodsTransportByIds(ids);
    }

}
