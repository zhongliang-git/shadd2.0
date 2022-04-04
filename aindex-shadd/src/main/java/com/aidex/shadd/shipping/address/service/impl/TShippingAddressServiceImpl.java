package com.aidex.shadd.shipping.address.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.common.exception.CustomException;
import com.aidex.shadd.shipping.address.domain.TDistributionService;
import com.aidex.shadd.shipping.address.domain.TShippingAddress;
import com.aidex.shadd.shipping.address.mapper.TDistributionServiceMapper;
import com.aidex.shadd.shipping.address.mapper.TShippingAddressMapper;
import com.aidex.shadd.shipping.address.service.TShippingAddressService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 收货地址Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
@Service
@Transactional(readOnly = true)
public class TShippingAddressServiceImpl extends BaseServiceImpl<TShippingAddressMapper, TShippingAddress> implements TShippingAddressService
{
    private static final Logger log = LoggerFactory.getLogger(TShippingAddressServiceImpl.class);
        @Autowired(required = false)
        private TDistributionServiceMapper tDistributionServiceMapper;

    /**
     * 获取单条数据
     * @param tShippingAddress 收货地址
     * @return 收货地址
     */
    @Override
    public TShippingAddress get(TShippingAddress tShippingAddress)
    {
        TShippingAddress dto = super.get(tShippingAddress);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 收货地址id
     * @return 收货地址
     */
    @Override
    public TShippingAddress get(String id)
    {
        TShippingAddress dto = super.get(id);
        return dto;
    }


    /**
     * 查询收货地址列表
     * @param tShippingAddress 收货地址
     * @return 收货地址
     */
    @Override
    public List<TShippingAddress> findList(TShippingAddress tShippingAddress)
    {
        return super.findList(tShippingAddress);
    }

    /**
     * 分页查询收货地址列表
     * @param tShippingAddress 收货地址
     * @return 收货地址
     */
    @Override
    public PageInfo<TShippingAddress> findPage(TShippingAddress tShippingAddress)
    {
        return super.findPage(tShippingAddress);
    }

    /**
     * 保存收货地址
     * @param tShippingAddress
     * @return 结果
     */
    @Override
    public boolean save(TShippingAddress tShippingAddress)
    {
        return super.save(tShippingAddress);
    }


    /**
     * 删除收货地址信息
     * @param tShippingAddress
     * @return 结果
     */
    @Override
    public boolean remove(TShippingAddress tShippingAddress)
    {
        return super.remove(tShippingAddress);
    }

    /**
     * 批量删除收货地址
     * @param ids 需要删除的收货地址ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTShippingAddressByIds(String[] ids)
    {
        for (String id : ids)
        {
            TDistributionService tDistributionService = new TDistributionService();
            tDistributionService.setReceivingId(id);
            List<TDistributionService> tDistributionServiceList = tDistributionServiceMapper.findList(tDistributionService);
            if (tDistributionServiceList.size() > 0)
            {
                throw new CustomException("子表存在数据,不能删除");
            }
        }
        return mapper.deleteTShippingAddressByIds(ids);
    }

}
