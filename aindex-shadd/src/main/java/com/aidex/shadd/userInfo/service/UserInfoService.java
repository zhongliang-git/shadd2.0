package com.aidex.shadd.userInfo.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.userInfo.domain.UserInfo;

import java.math.BigDecimal;

/**
 * 用户信息Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
public interface UserInfoService extends BaseService<UserInfo>
{
    String USER_BALANCE = "USER-BALANCE:";

    /**
     * 批量删除用户信息
     * @param ids 需要删除的用户信息ID集合
     * @return 结果
     */
    int deleteUserInfoByIds(String[] ids);

    /**
     * 批量删除用户信息
     * @param userId 用户Id
     * @param money 金额
     * @return 结果
     */
    boolean addBalance(String userId, BigDecimal money);

    /**
     * 用户信息查询
     * @param: id 用户Id
     * @return userInfo 用户信息
     * @author zhongliang
     * @date: 2022/4/4 3:26
     */
    UserInfo findUserInfo(String id);
}
