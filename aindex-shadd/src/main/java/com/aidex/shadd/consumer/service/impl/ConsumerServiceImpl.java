package com.aidex.shadd.consumer.service.impl;

import com.aidex.common.core.domain.entity.SysUser;
import com.aidex.common.exception.SysException;
import com.aidex.common.utils.StringUtils;
import com.aidex.shadd.consumer.domain.ConsumerModel;
import com.aidex.shadd.consumer.service.IConsumerService;
import com.aidex.shadd.tInvitationcode.domain.TInvitationCode;
import com.aidex.shadd.tInvitationcode.service.TInvitationCodeService;
import com.aidex.shadd.util.StaticPoolUtil;
import com.aidex.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zhongliang
 * @version 1.0
 * @description: 客户服务接口实现
 * @date 2022/4/4 0:07
 */
@Service
@Transactional(readOnly = true)
public class ConsumerServiceImpl implements IConsumerService {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private TInvitationCodeService invitationCodeService;

    @Override
    @Transactional(readOnly = false)
    public ConsumerModel register(ConsumerModel consumerModel) {
        TInvitationCode invitationCode = this.invCodeVerify(consumerModel);
        SysUser user = consumerModel.getUser();
        // 设置默认组织 - 客户
        user.setDeptId("a4ebdf9c810f4a9e84252a17825a18ac");
        // 设置默认岗位 - 普通用户
        String[] postIds = new String[]{"4"};
        user.setPostIds(postIds);
        // 设置默认角色 - 一般用户
        String[] roleIds = new String[]{"c6676868d24e43098e4724014a096800"};
        user.setRoleIds(roleIds);
        // 设置默认密码
        user.setPassword(StaticPoolUtil.INIT_PASS);
        // 设置状态 - 正常
        user.setStatus("0");
        // 设置性别
        user.setSex("2");
        // 设置用户类型
        user.setUserType("2");
        // 新增用户
        userService.insertUser(user);
        // 更新邀请码关联人员
        invitationCode.setUserid(user.getId());
        // 更新邀请码
        invitationCodeService.save(invitationCode);
        return consumerModel;
    }


    /**
     * 验证邀请码
     * @param: consumerModel 用户信息
     * @author zhongliang
     * @date: 2022/4/4 0:27
     */
    private TInvitationCode invCodeVerify(ConsumerModel consumerModel) {
        String invCode = consumerModel.getInviteCode();
        if (StringUtils.isBlank(invCode)) {
            throw new SysException("邀请码必填，无邀请码？请联系管理员!");
        }
        // 验证邀请码
        TInvitationCode invitationCode = new TInvitationCode();
        invitationCode.setCode(invCode);
        List<TInvitationCode> invitationCodes = invitationCodeService.findList(invitationCode);
        if (CollectionUtils.isEmpty(invitationCodes)) {
            throw new SysException("邀请码验证失败，无邀请码？请联系管理员!");
        }
        invitationCode = invitationCodes.get(0);
        if (StringUtils.isNotBlank(invitationCode.getUserid())) {
            throw new SysException("邀请码验证已被其他人使用？请联系管理员!");
        }
        return invitationCode;
    }
}
