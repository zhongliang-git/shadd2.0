package com.aidex.shadd.releaserecords.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.common.exception.CustomException;
import com.aidex.shadd.releaserecords.domain.TGoodsTransport;
import com.aidex.shadd.releaserecords.domain.TReleaseRecords;
import com.aidex.shadd.releaserecords.mapper.TGoodsTransportMapper;
import com.aidex.shadd.releaserecords.mapper.TReleaseRecordsMapper;
import com.aidex.shadd.releaserecords.service.TReleaseRecordsService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 发货记录Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-16
 */
@Service
@Transactional(readOnly = true)
public class TReleaseRecordsServiceImpl extends BaseServiceImpl<TReleaseRecordsMapper, TReleaseRecords> implements TReleaseRecordsService
{
    private static final Logger log = LoggerFactory.getLogger(TReleaseRecordsServiceImpl.class);
        @Autowired(required = false)
        private TGoodsTransportMapper tGoodsTransportMapper;

    /**
     * 获取单条数据
     * @param tReleaseRecords 发货记录
     * @return 发货记录
     */
    @Override
    public TReleaseRecords get(TReleaseRecords tReleaseRecords)
    {
        TReleaseRecords dto = super.get(tReleaseRecords);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 发货记录id
     * @return 发货记录
     */
    @Override
    public TReleaseRecords get(String id)
    {
        TReleaseRecords dto = super.get(id);
        return dto;
    }


    /**
     * 查询发货记录列表
     * @param tReleaseRecords 发货记录
     * @return 发货记录
     */
    @Override
    public List<TReleaseRecords> findList(TReleaseRecords tReleaseRecords)
    {
        return super.findList(tReleaseRecords);
    }

    /**
     * 分页查询发货记录列表
     * @param tReleaseRecords 发货记录
     * @return 发货记录
     */
    @Override
    public PageInfo<TReleaseRecords> findPage(TReleaseRecords tReleaseRecords)
    {
        return super.findPage(tReleaseRecords);
    }

    /**
     * 保存发货记录
     * @param tReleaseRecords
     * @return 结果
     */
    @Override
    public boolean save(TReleaseRecords tReleaseRecords)
    {
        return super.save(tReleaseRecords);
    }


    /**
     * 删除发货记录信息
     * @param tReleaseRecords
     * @return 结果
     */
    @Override
    public boolean remove(TReleaseRecords tReleaseRecords)
    {
        return super.remove(tReleaseRecords);
    }

    /**
     * 批量删除发货记录
     * @param ids 需要删除的发货记录ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTReleaseRecordsByIds(String[] ids)
    {
        for (String id : ids)
        {
            TGoodsTransport tGoodsTransport = new TGoodsTransport();
            tGoodsTransport.setRelid(id);
            List<TGoodsTransport> tGoodsTransportList = tGoodsTransportMapper.findList(tGoodsTransport);
            if (tGoodsTransportList.size() > 0)
            {
                throw new CustomException("子表存在数据,不能删除");
            }
        }
        return mapper.deleteTReleaseRecordsByIds(ids);
    }

}
