package com.aidex.shadd.product.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.shadd.product.domain.TProduct;
import com.aidex.shadd.product.mapper.TProductMapper;
import com.aidex.shadd.product.service.TProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-10
 */
@Service
@Transactional(readOnly = true)
public class TProductServiceImpl extends BaseServiceImpl<TProductMapper, TProduct> implements TProductService
{
    private static final Logger log = LoggerFactory.getLogger(TProductServiceImpl.class);

    /**
     * 获取单条数据
     * @param tProduct 产品
     * @return 产品
     */
    @Override
    public TProduct get(TProduct tProduct)
    {
        TProduct dto = super.get(tProduct);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 产品id
     * @return 产品
     */
    @Override
    public TProduct get(String id)
    {
        TProduct dto = super.get(id);
        return dto;
    }


    /**
     * 查询产品列表
     * @param tProduct 产品
     * @return 产品
     */
    @Override
    public List<TProduct> findList(TProduct tProduct)
    {
        return super.findList(tProduct);
    }

    /**
     * 分页查询产品列表
     * @param tProduct 产品
     * @return 产品
     */
    @Override
    public PageInfo<TProduct> findPage(TProduct tProduct)
    {
        return super.findPage(tProduct);
    }

    /**
     * 保存产品
     * @param tProduct
     * @return 结果
     */
    @Override
    public boolean save(TProduct tProduct)
    {
        return super.save(tProduct);
    }


    /**
     * 删除产品信息
     * @param tProduct
     * @return 结果
     */
    @Override
    public boolean remove(TProduct tProduct)
    {
        return super.remove(tProduct);
    }

    /**
     * 批量删除产品
     * @param ids 需要删除的产品ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTProductByIds(String[] ids)
    {
        return mapper.deleteTProductByIds(ids);
    }

    @Override
    public List<TProduct> queryListOrBy(TProduct product) {
        return mapper.queryListOrBy(product);
    }

}
