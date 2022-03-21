package com.aidex.shadd.releaserecords.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.shadd.releaserecords.domain.TGoodsTransport;
import com.aidex.shadd.releaserecords.domain.TReleaseRecords;
import com.aidex.shadd.releaserecords.mapper.TGoodsTransportMapper;
import com.aidex.shadd.releaserecords.service.TGoodsTransportService;
import com.aidex.shadd.releaserecords.service.TReleaseRecordsService;
import com.aidex.shadd.saleproduct.domain.TRepertory;
import com.aidex.shadd.saleproduct.domain.TSaleProduct;
import com.aidex.shadd.saleproduct.service.TSaleProductService;
import com.aidex.shadd.shippingline.domain.TShippingLine;
import com.aidex.shadd.shippingline.service.TShippingLineService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    TSaleProductService saleProductService;
    @Autowired
    TReleaseRecordsService releaseRecordsService;
    @Autowired
    TShippingLineService shippingLineService;

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
        boolean status = super.save(tGoodsTransport);
        if (status && "到达".equals(tGoodsTransport.getBillstatus())) {
            // 增加库存
            TSaleProduct saleProduct = new TSaleProduct();
            // 根据发货记录查询采购产品Id
            String relId = tGoodsTransport.getRelid();
            TReleaseRecords records = releaseRecordsService.get(relId);
            // 采购产品Id
            saleProduct.setProductid(records.getProductid());
            // 根据铁路运线查询销售站点Id
            TShippingLine shippingLine = shippingLineService.get(tGoodsTransport.getLineid());
            // 销售站点ID
            saleProduct.setSiteid(shippingLine.getSeceivingsiteid());
            TRepertory repertory = new TRepertory();
            // 库存
            repertory.setRepertory(tGoodsTransport.getNumber());
            saleProduct.setRepertory(repertory);
            saleProductService.saveOrUpdate(saleProduct);
        }
        return status;
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
