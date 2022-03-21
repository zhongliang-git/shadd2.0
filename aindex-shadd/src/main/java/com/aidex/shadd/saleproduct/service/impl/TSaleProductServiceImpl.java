package com.aidex.shadd.saleproduct.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.common.exception.CustomException;
import com.aidex.common.exception.SysException;
import com.aidex.shadd.saleproduct.domain.TRepertory;
import com.aidex.shadd.saleproduct.domain.TSaleProduct;
import com.aidex.shadd.saleproduct.mapper.TRepertoryMapper;
import com.aidex.shadd.saleproduct.mapper.TSaleProductMapper;
import com.aidex.shadd.saleproduct.service.TRepertoryService;
import com.aidex.shadd.saleproduct.service.TSaleProductService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 销售产品Service业务层处理
 *
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
@Service
@Transactional(readOnly = true)
public class TSaleProductServiceImpl extends BaseServiceImpl<TSaleProductMapper, TSaleProduct> implements TSaleProductService {
    private static final Logger log = LoggerFactory.getLogger(TSaleProductServiceImpl.class);
    @Autowired(required = false)
    private TRepertoryMapper tRepertoryMapper;

    @Autowired(required = false)
    private TRepertoryService repertoryService;


    /**
     * 获取单条数据
     *
     * @param tSaleProduct 销售产品
     * @return 销售产品
     */
    @Override
    public TSaleProduct get(TSaleProduct tSaleProduct) {
        TSaleProduct dto = super.get(tSaleProduct);
        return dto;
    }

    /**
     * 获取单条数据
     *
     * @param id 销售产品id
     * @return 销售产品
     */
    @Override
    public TSaleProduct get(String id) {
        TSaleProduct dto = super.get(id);
        return dto;
    }


    /**
     * 查询销售产品列表
     *
     * @param tSaleProduct 销售产品
     * @return 销售产品
     */
    @Override
    public List<TSaleProduct> findList(TSaleProduct tSaleProduct) {
        return super.findList(tSaleProduct);
    }

    /**
     * 分页查询销售产品列表
     *
     * @param tSaleProduct 销售产品
     * @return 销售产品
     */
    @Override
    public PageInfo<TSaleProduct> findPage(TSaleProduct tSaleProduct) {
        return super.findPage(tSaleProduct);
    }

    /**
     * 保存销售产品
     *
     * @param tSaleProduct
     * @return 结果
     */
    @Override
    public boolean save(TSaleProduct tSaleProduct) {
        return super.save(tSaleProduct);
    }


    /**
     * 删除销售产品信息
     *
     * @param tSaleProduct
     * @return 结果
     */
    @Override
    public boolean remove(TSaleProduct tSaleProduct) {
        return super.remove(tSaleProduct);
    }

    /**
     * 批量删除销售产品
     *
     * @param ids 需要删除的销售产品ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTSaleProductByIds(String[] ids) {
        for (String id : ids) {
            TRepertory tRepertory = new TRepertory();
            tRepertory.setSpid(id);
            List<TRepertory> tRepertoryList = tRepertoryMapper.findList(tRepertory);
            if (tRepertoryList.size() > 0) {
                throw new CustomException("子表存在数据,不能删除");
            }
        }
        return mapper.deleteTSaleProductByIds(ids);
    }

    public boolean saveOrUpdate(TSaleProduct tSaleProduct) {
        {
            // 查询销售产品是否存在，如果存在，修改库存、否则新增销售产品+库存
            TSaleProduct newSaleProduct = new TSaleProduct();
            String productid = tSaleProduct.getProductid();
            String siteid = tSaleProduct.getSiteid();
            if (StringUtil.isEmpty(productid)) {
                throw new SysException("采购产品不允许空");
            }
            if (StringUtil.isEmpty(siteid)) {
                throw new SysException("销售站线不允许空");
            }
            newSaleProduct.setProductid(productid);
            newSaleProduct.setSiteid(siteid);
            List<TSaleProduct> saleProducts = mapper.findList(newSaleProduct);
            TRepertory repertory = tSaleProduct.getRepertory();
            if (repertory == null) {
                throw new SysException("库存不允许空");
            }

            if (CollectionUtils.isEmpty(saleProducts)) {
                super.save(tSaleProduct);
                repertory.setSpid(tSaleProduct.getId());
                // 新增库存
                repertoryService.saveByRepertoryNumber(tSaleProduct.getRepertory());
                return true;
            }
            repertory.setSpid(saleProducts.get(0).getId());
            // 修改库存
            repertoryService.modifyRepertoryNumber(tSaleProduct.getRepertory());

            return true;
        }
    }

    @Override
    public PageInfo<TSaleProduct> findGoodsPage(TSaleProduct saleProduct) {
        this.page(saleProduct);
        List<TSaleProduct> saleProducts = mapper.queryGoods(saleProduct);
        return new PageInfo<>(saleProducts);
    }
}
