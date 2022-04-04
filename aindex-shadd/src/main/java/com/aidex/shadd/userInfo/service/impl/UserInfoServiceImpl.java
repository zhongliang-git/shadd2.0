package com.aidex.shadd.userInfo.service.impl;

import com.aidex.common.core.domain.entity.SysUser;
import com.aidex.common.core.redis.RedisLock;
import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.common.exception.SysException;
import com.aidex.framework.cache.UserUtils;
import com.aidex.shadd.userInfo.domain.UserInfo;
import com.aidex.shadd.userInfo.mapper.UserInfoMapper;
import com.aidex.shadd.userInfo.service.UserInfoService;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户信息Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
@Service
@Transactional(readOnly = true)
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService
{
    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private RedisLock redisLock;

    /**
     * 获取单条数据
     * @param userInfo 用户信息
     * @return 用户信息
     */
    @Override
    public UserInfo get(UserInfo userInfo)
    {
        UserInfo dto = super.get(userInfo);
                SysUser sysUser = UserUtils.getSysUser(dto.getUserId());
            dto.setUserIdName(sysUser.getName());
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 用户信息id
     * @return 用户信息
     */
    @Override
    public UserInfo get(String id)
    {
        UserInfo dto = super.get(id);
                SysUser sysUser = UserUtils.getSysUser(dto.getUserId());
                dto.setUserIdName(sysUser.getName());
        return dto;
    }


    /**
     * 查询用户信息列表
     * @param userInfo 用户信息
     * @return 用户信息
     */
    @Override
    public List<UserInfo> findList(UserInfo userInfo)
    {
        return super.findList(userInfo);
    }

    /**
     * 分页查询用户信息列表
     * @param userInfo 用户信息
     * @return 用户信息
     */
    @Override
    public PageInfo<UserInfo> findPage(UserInfo userInfo)
    {
        return super.findPage(userInfo);
    }

    /**
     * 保存用户信息
     * @param userInfo
     * @return 结果
     */
    @Override
    public boolean save(UserInfo userInfo)
    {
        return super.save(userInfo);
    }


    /**
     * 删除用户信息信息
     * @param userInfo
     * @return 结果
     */
    @Override
    public boolean remove(UserInfo userInfo)
    {
        return super.remove(userInfo);
    }

    /**
     * 批量删除用户信息
     * @param ids 需要删除的用户信息ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteUserInfoByIds(String[] ids)
    {
        return mapper.deleteUserInfoByIds(ids);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean addBalance(String userId, BigDecimal money) {
        String key = this.getLock(userId);
        try{
            // 查询用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            if (CollectionUtils.isEmpty(this.findList(userInfo))) {
                // 如果用户信息不存在，初始化用户信息
                userInfo.setBalance(money);
                userInfo.setTotal(money);
                userInfo.setIntegrate(0L);
                this.save(userInfo);
                return true;
            } else {
                return mapper.updateBalanceAdd(userId, money) > 0;
            }
        } finally {
            redisLock.unlock(key);
        }
    }

    @Override
    public UserInfo findUserInfo(String id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id);
        List<UserInfo> userInfos = this.findList(userInfo);
        if (CollectionUtils.isEmpty(userInfos)) {
            return null;
        }
        return userInfos.get(0);
    }

    @SneakyThrows
    private String getLock(String userId) {
        String key = USER_BALANCE + userId;
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
