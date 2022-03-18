package com.aidex.shadd.shippingline.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.shadd.shippingline.domain.TShippingLine;
import com.aidex.shadd.shippingline.mapper.TShippingLineMapper;
import com.aidex.shadd.shippingline.service.TShippingLineService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 运线Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-16
 */
@Service
@Transactional(readOnly = true)
public class TShippingLineServiceImpl extends BaseServiceImpl<TShippingLineMapper, TShippingLine> implements TShippingLineService
{
    private static final Logger log = LoggerFactory.getLogger(TShippingLineServiceImpl.class);

    /**
     * 获取单条数据
     * @param tShippingLine 运线
     * @return 运线
     */
    @Override
    public TShippingLine get(TShippingLine tShippingLine)
    {
        TShippingLine dto = super.get(tShippingLine);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 运线id
     * @return 运线
     */
    @Override
    public TShippingLine get(String id)
    {
        TShippingLine dto = super.get(id);
        return dto;
    }


    /**
     * 查询运线列表
     * @param tShippingLine 运线
     * @return 运线
     */
    @Override
    public List<TShippingLine> findList(TShippingLine tShippingLine)
    {
        return super.findList(tShippingLine);
    }

    /**
     * 分页查询运线列表
     * @param tShippingLine 运线
     * @return 运线
     */
    @Override
    public PageInfo<TShippingLine> findPage(TShippingLine tShippingLine)
    {
        return super.findPage(tShippingLine);
    }

    /**
     * 保存运线
     * @param tShippingLine
     * @return 结果
     */
    @Override
    public boolean save(TShippingLine tShippingLine)
    {
        return super.save(tShippingLine);
    }


    /**
     * 删除运线信息
     * @param tShippingLine
     * @return 结果
     */
    @Override
    public boolean remove(TShippingLine tShippingLine)
    {
        return super.remove(tShippingLine);
    }

    /**
     * 批量删除运线
     * @param ids 需要删除的运线ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTShippingLineByIds(String[] ids)
    {
        return mapper.deleteTShippingLineByIds(ids);
    }

}
