package com.aidex.shadd.saleproduct.service.impl;

import com.aidex.common.core.redis.RedisLock;
import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.common.exception.SysException;
import com.aidex.shadd.saleproduct.domain.TRepertory;
import com.aidex.shadd.saleproduct.mapper.TRepertoryMapper;
import com.aidex.shadd.saleproduct.service.TRepertoryService;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 库存子表Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
@Service
@Transactional(readOnly = true)
public class TRepertoryServiceImpl extends BaseServiceImpl<TRepertoryMapper, TRepertory> implements TRepertoryService
{
    private static final Logger log = LoggerFactory.getLogger(TRepertoryServiceImpl.class);

    @Autowired(required = false)
    private RedisLock redisLock;

    /**
     * 获取单条数据
     * @param tRepertory 库存子表
     * @return 库存子表
     */
    @Override
    public TRepertory get(TRepertory tRepertory)
    {
        TRepertory dto = super.get(tRepertory);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 库存子表id
     * @return 库存子表
     */
    @Override
    public TRepertory get(String id)
    {
        TRepertory dto = super.get(id);
        return dto;
    }


    /**
     * 查询库存子表列表
     * @param tRepertory 库存子表
     * @return 库存子表
     */
    @Override
    public List<TRepertory> findList(TRepertory tRepertory)
    {
        return super.findList(tRepertory);
    }

    /**
     * 分页查询库存子表列表
     * @param tRepertory 库存子表
     * @return 库存子表
     */
    @Override
    public PageInfo<TRepertory> findPage(TRepertory tRepertory)
    {
        return super.findPage(tRepertory);
    }

    /**
     * 保存库存子表
     * @param tRepertory
     * @return 结果
     */
    @Override
    public boolean save(TRepertory tRepertory)
    {
        return super.save(tRepertory);
    }


    /**
     * 删除库存子表信息
     * @param tRepertory
     * @return 结果
     */
    @Override
    public boolean remove(TRepertory tRepertory)
    {
        return super.remove(tRepertory);
    }

    /**
     * 批量删除库存子表
     * @param ids 需要删除的库存子表ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTRepertoryByIds(String[] ids)
    {
        return mapper.deleteTRepertoryByIds(ids);
    }

    @Override
    public boolean saveByRepertoryNumber(TRepertory repertory) {
        if (repertory == null) {
            return false;
        }
        if (repertory.getRepertory() == null) {
            return false;
        }
        repertory.setClsd(new BigDecimal(0));
        repertory.setObligation(new BigDecimal(0));
        repertory.setTotalAmount(new BigDecimal(0));
        return super.save(repertory);
    }

    @Override
    public boolean modifyRepertoryNumber(TRepertory repertory) {
        String key = this.getLock(repertory);
        try {
            // 修改库存
            return mapper.updateRepertory(repertory) > 0;
        } finally {
            redisLock.unlock(key);
        }
    }

    @Override
    public boolean repertorySales(TRepertory repertory) {
        String key = this.getLock(repertory);
        try {
            // 如果更新条数小于1，证明库存不住，抛出异常
            if (mapper.updateProduct(repertory) < 1) {
                throw new SysException("库存不足,请联系在线客服");
            }
            return true;
        } finally {
            redisLock.unlock(key);
        }
    }

    @SneakyThrows
    private String getLock(TRepertory repertory) {
        String key = REPERTORY_KEY + repertory.getSpid();
        int i = 0;
        // 6次未获取到锁，不再获取
        boolean lock = redisLock.lock(key);
        while (i < 6 && !lock) {
            Thread.currentThread().sleep(1000);
            i ++;
            lock = redisLock.lock(key);
        }
        if (!lock) {
            throw new SysException("系统繁忙，请稍后再试!");
        }
        return key;
    }
}
