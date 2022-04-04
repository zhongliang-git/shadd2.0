package com.aidex.shadd.consumer.domain;

import com.aidex.common.core.domain.entity.SysUser;
import com.aidex.shadd.userInfo.domain.UserInfo;
import lombok.Data;

/**
 * @author zhongliang
 * @version 1.0
 * @description: 客户实例
 * @date 2022/3/28 22:43
 */
@Data
public class ConsumerModel {

    private static final long serialVersionUID = 1L;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 用户信息
     */
    private UserInfo userInfo;

    /**
     * 用户
     */
    private SysUser user;

}
