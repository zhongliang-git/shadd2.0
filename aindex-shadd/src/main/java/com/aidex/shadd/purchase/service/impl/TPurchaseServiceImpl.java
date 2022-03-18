package com.aidex.shadd.purchase.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.common.exception.BizException;
import com.aidex.common.utils.StringUtils;
import com.aidex.shadd.purchase.domain.TPurchase;
import com.aidex.shadd.purchase.mapper.TOfferMapper;
import com.aidex.shadd.purchase.mapper.TPurchaseMapper;
import com.aidex.shadd.purchase.service.TPurchaseService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 采购产品Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
@Service
@Transactional(readOnly = true)
public class TPurchaseServiceImpl extends BaseServiceImpl<TPurchaseMapper, TPurchase> implements TPurchaseService
{
    private static final Logger log = LoggerFactory.getLogger(TPurchaseServiceImpl.class);
        @Autowired(required = false)
        private TOfferMapper tOfferMapper;
        @Autowired
        private TPurchaseMapper purchaseMapper;
    /**
     * 获取单条数据
     * @param tPurchase 采购产品
     * @return 采购产品
     */
    @Override
    public TPurchase get(TPurchase tPurchase)
    {
        TPurchase dto = super.get(tPurchase);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 采购产品id
     * @return 采购产品
     */
    @Override
    public TPurchase get(String id)
    {
        TPurchase dto = super.get(id);
        return dto;
    }


    /**
     * 查询采购产品列表
     * @param tPurchase 采购产品
     * @return 采购产品
     */
    @Override
    public List<TPurchase> findList(TPurchase tPurchase)
    {
        return super.findList(tPurchase);
    }

    /**
     * 分页查询采购产品列表
     * @param tPurchase 采购产品
     * @return 采购产品
     */
    @Override
    public PageInfo<TPurchase> findPage(TPurchase tPurchase)
    {
        return super.findPage(tPurchase);
    }

    /**
     * 保存采购产品
     * @param tPurchase
     * @return 结果
     */
    @Override
    public boolean save(TPurchase tPurchase)
    {
        return super.save(tPurchase);
    }


    /**
     * 删除采购产品信息
     * @param tPurchase
     * @return 结果
     */
    @Override
    public boolean remove(TPurchase tPurchase)
    {
        return super.remove(tPurchase);
    }

    /**
     * 批量删除采购产品
     * @param ids 需要删除的采购产品ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTPurchaseByIds(String[] ids)
    {
        for (int i = 0; i < ids.length; i++) {
            // 删除相关的采购价
            tOfferMapper.deleteByPurchaseId(ids[i]);
        }
        return mapper.deleteTPurchaseByIds(ids);
    }

    @Override
    public void removeByFactoryId(String factoryId) {
        TPurchase purchase = new TPurchase();
        purchase.setFactoryid(factoryId);
        List<TPurchase> purchaseList = this.findList(purchase);
        if (CollectionUtils.isEmpty(purchaseList)) {
            return;
        }
        List<String> purchaseIds = purchaseList.stream().map(TPurchase::getId).distinct().collect(Collectors.toList());
        String[] ids = purchaseIds.toArray(new String[purchaseIds.size()]);
        this.deleteTPurchaseByIds(ids);
    }

    @Override
    public PageInfo<TPurchase> findPurchaseListPage(TPurchase tPurchase) {
        if (tPurchase == null || StringUtils.isBlank(tPurchase.getFactoryid())) {
            throw new BizException("请先选择厂家");
        }
        this.page(tPurchase);
        return new PageInfo<TPurchase>(purchaseMapper.findPurchaseList(tPurchase));
    }

}
