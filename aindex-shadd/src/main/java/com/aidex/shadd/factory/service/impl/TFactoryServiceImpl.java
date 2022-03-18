package com.aidex.shadd.factory.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.shadd.factory.domain.TFactory;
import com.aidex.shadd.factory.mapper.TFactoryMapper;
import com.aidex.shadd.factory.service.TFactoryService;
import com.aidex.shadd.purchase.domain.TOffer;
import com.aidex.shadd.purchase.domain.TPurchase;
import com.aidex.shadd.purchase.service.TOfferService;
import com.aidex.shadd.purchase.service.TPurchaseService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    TFactoryMapper tFactoryMapper;
    @Autowired
    private TPurchaseService purchaseService;
    @Autowired
    private TOfferService offerService;

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

    @Override
    public TFactory findTFactoryById(String id) {
        return mapper.queryTFactoryById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public TFactory modifyTFactory(TFactory tFactory) {
        this.save(tFactory);
        List<TPurchase> purchaseList = tFactory.getPurchaseList();
        if (CollectionUtils.isEmpty(purchaseList)) {
            return tFactory;
        }
        String factoryId = tFactory.getId();
        // 删除历史采购产品 再新增
        purchaseService.removeByFactoryId(factoryId);
        List<TOffer> offers = new ArrayList<>(16);
        purchaseList.forEach(data -> {
            data.setFactoryid(factoryId);
            data.setIsNewRecord(Boolean.TRUE);
            purchaseService.save(data);
            String purId = data.getId();
            data.getOffers().stream().forEach(item->item.setPurchaseId(purId));
            offers.addAll(data.getOffers());
        });
        offers.forEach(data -> {
            data.setIsNewRecord(Boolean.TRUE);
            offerService.save(data);
        });

        return tFactory;
    }

    @Override
    public PageInfo<TFactory> findListByName(TFactory tFactory) {
        this.page(tFactory);
        return new PageInfo<TFactory>(tFactoryMapper.findListByName(tFactory));
    }

}
