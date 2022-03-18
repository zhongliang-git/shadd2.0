package com.aidex.shadd.purchase.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.shadd.purchase.domain.TOffer;
import com.aidex.shadd.purchase.mapper.TOfferMapper;
import com.aidex.shadd.purchase.service.TOfferService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 厂家报价子表Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
@Service
@Transactional(readOnly = true)
public class TOfferServiceImpl extends BaseServiceImpl<TOfferMapper, TOffer> implements TOfferService
{
    private static final Logger log = LoggerFactory.getLogger(TOfferServiceImpl.class);

    /**
     * 获取单条数据
     * @param tOffer 厂家报价子表
     * @return 厂家报价子表
     */
    @Override
    public TOffer get(TOffer tOffer)
    {
        TOffer dto = super.get(tOffer);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 厂家报价子表id
     * @return 厂家报价子表
     */
    @Override
    public TOffer get(String id)
    {
        TOffer dto = super.get(id);
        return dto;
    }


    /**
     * 查询厂家报价子表列表
     * @param tOffer 厂家报价子表
     * @return 厂家报价子表
     */
    @Override
    public List<TOffer> findList(TOffer tOffer)
    {
        return super.findList(tOffer);
    }

    /**
     * 分页查询厂家报价子表列表
     * @param tOffer 厂家报价子表
     * @return 厂家报价子表
     */
    @Override
    public PageInfo<TOffer> findPage(TOffer tOffer)
    {
        return super.findPage(tOffer);
    }

    /**
     * 保存厂家报价子表
     * @param tOffer
     * @return 结果
     */
    @Override
    public boolean save(TOffer tOffer)
    {
        return super.save(tOffer);
    }


    /**
     * 删除厂家报价子表信息
     * @param tOffer
     * @return 结果
     */
    @Override
    public boolean remove(TOffer tOffer)
    {
        return super.remove(tOffer);
    }

    /**
     * 批量删除厂家报价子表
     * @param ids 需要删除的厂家报价子表ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTOfferByIds(String[] ids)
    {
        return mapper.deleteTOfferByIds(ids);
    }

}
