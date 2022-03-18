package com.aidex.shadd.saleproduct.service.impl;

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
import com.aidex.shadd.saleproduct.mapper.TSaleProductMapper;
import com.aidex.shadd.saleproduct.domain.TSaleProduct;
import com.aidex.shadd.saleproduct.service.TSaleProductService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 销售产品Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
@Service
@Transactional(readOnly = true)
public class TSaleProductServiceImpl extends BaseServiceImpl<TSaleProductMapper, TSaleProduct> implements TSaleProductService
{
    private static final Logger log = LoggerFactory.getLogger(TSaleProductServiceImpl.class);

    /**
     * 获取单条数据
     * @param tSaleProduct 销售产品
     * @return 销售产品
     */
    @Override
    public TSaleProduct get(TSaleProduct tSaleProduct)
    {
        TSaleProduct dto = super.get(tSaleProduct);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 销售产品id
     * @return 销售产品
     */
    @Override
    public TSaleProduct get(String id)
    {
        TSaleProduct dto = super.get(id);
        return dto;
    }


    /**
     * 查询销售产品列表
     * @param tSaleProduct 销售产品
     * @return 销售产品
     */
    @Override
    public List<TSaleProduct> findList(TSaleProduct tSaleProduct)
    {
        return super.findList(tSaleProduct);
    }

    /**
     * 分页查询销售产品列表
     * @param tSaleProduct 销售产品
     * @return 销售产品
     */
    @Override
    public PageInfo<TSaleProduct> findPage(TSaleProduct tSaleProduct)
    {
        return super.findPage(tSaleProduct);
    }

    /**
     * 保存销售产品
     * @param tSaleProduct
     * @return 结果
     */
    @Override
    public boolean save(TSaleProduct tSaleProduct)
    {
        return super.save(tSaleProduct);
    }


    /**
     * 删除销售产品信息
     * @param tSaleProduct
     * @return 结果
     */
    @Override
    public boolean remove(TSaleProduct tSaleProduct)
    {
        return super.remove(tSaleProduct);
    }

    /**
     * 批量删除销售产品
     * @param ids 需要删除的销售产品ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTSaleProductByIds(String[] ids)
    {
        return mapper.deleteTSaleProductByIds(ids);
    }

}
