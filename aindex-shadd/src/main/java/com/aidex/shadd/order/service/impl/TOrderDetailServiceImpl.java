package com.aidex.shadd.order.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.shadd.order.domain.TOrderDetail;
import com.aidex.shadd.order.mapper.TOrderDetailMapper;
import com.aidex.shadd.order.service.TOrderDetailService;
import com.aidex.shadd.saleproduct.domain.TRepertory;
import com.aidex.shadd.saleproduct.service.TRepertoryService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单明细子表Service业务层处理
 * @author ${functionAuthor}
 * @email ${functionAuthorEmail}
 * @date 2022-04-07
 */
@Service
@Transactional(readOnly = true)
public class TOrderDetailServiceImpl extends BaseServiceImpl<TOrderDetailMapper, TOrderDetail> implements TOrderDetailService
{
    private static final Logger log = LoggerFactory.getLogger(TOrderDetailServiceImpl.class);

    /**
     * 获取单条数据
     * @param tOrderDetail 订单明细子表
     * @return 订单明细子表
     */
    @Override
    public TOrderDetail get(TOrderDetail tOrderDetail)
    {
        TOrderDetail dto = super.get(tOrderDetail);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 订单明细子表id
     * @return 订单明细子表
     */
    @Override
    public TOrderDetail get(String id)
    {
        TOrderDetail dto = super.get(id);
        return dto;
    }


    /**
     * 查询订单明细子表列表
     * @param tOrderDetail 订单明细子表
     * @return 订单明细子表
     */
    @Override
    public List<TOrderDetail> findList(TOrderDetail tOrderDetail)
    {
        return super.findList(tOrderDetail);
    }

    /**
     * 分页查询订单明细子表列表
     * @param tOrderDetail 订单明细子表
     * @return 订单明细子表
     */
    @Override
    public PageInfo<TOrderDetail> findPage(TOrderDetail tOrderDetail)
    {
        return super.findPage(tOrderDetail);
    }

    /**
     * 保存订单明细子表
     * @param tOrderDetail
     * @return 结果
     */
    @Override
    public boolean save(TOrderDetail tOrderDetail)
    {
        return super.save(tOrderDetail);
    }


    /**
     * 删除订单明细子表信息
     * @param tOrderDetail
     * @return 结果
     */
    @Override
    public boolean remove(TOrderDetail tOrderDetail)
    {
        return super.remove(tOrderDetail);
    }

    /**
     * 批量删除订单明细子表
     * @param ids 需要删除的订单明细子表ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTOrderDetailByIds(String[] ids)
    {
        return mapper.deleteTOrderDetailByIds(ids);
    }

    @Autowired
    TRepertoryService repertoryService;

    @Override
    @Transactional(readOnly = false)
    public void generateOrderDetail(List<TOrderDetail> orderDetailList) {
        orderDetailList.forEach(orderDetail ->  {
            // 扣减库存
            TRepertory tRepertory = new TRepertory();
            tRepertory.setRepertory(orderDetail.getNum());
            tRepertory.setSpid(orderDetail.getProductId());
            repertoryService.repertorySales(tRepertory);
            super.save(orderDetail);
        });
    }

    @Override
    public List<TOrderDetail> findOrderDetail(String orderId) {
        TOrderDetail detail = new TOrderDetail();
        detail.setOrderId(orderId);
        return mapper.findList(detail);
    }

}
