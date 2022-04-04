package com.aidex.shadd.tInvitationcode.service.impl;

import com.aidex.common.core.domain.entity.SysUser;
import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.common.exception.BizException;
import com.aidex.framework.cache.UserUtils;
import com.aidex.shadd.tInvitationcode.domain.TInvitationCode;
import com.aidex.shadd.tInvitationcode.mapper.TInvitationCodeMapper;
import com.aidex.shadd.tInvitationcode.service.TInvitationCodeService;
import com.aidex.shadd.util.InviteCodeUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 邀请码Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
@Service
@Transactional(readOnly = true)
public class TInvitationCodeServiceImpl extends BaseServiceImpl<TInvitationCodeMapper, TInvitationCode> implements TInvitationCodeService
{
    private static final Logger log = LoggerFactory.getLogger(TInvitationCodeServiceImpl.class);

    /**
     * 获取单条数据
     * @param tInvitationCode 邀请码
     * @return 邀请码
     */
    @Override
    public TInvitationCode get(TInvitationCode tInvitationCode)
    {
        TInvitationCode dto = super.get(tInvitationCode);
                SysUser sysUser = UserUtils.getSysUser(dto.getUserid());
            dto.setUserName(sysUser.getName());
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 邀请码id
     * @return 邀请码
     */
    @Override
    public TInvitationCode get(String id)
    {
        TInvitationCode dto = super.get(id);
                SysUser sysUser = UserUtils.getSysUser(dto.getUserid());
                dto.setUserName(sysUser.getName());
        return dto;
    }


    /**
     * 查询邀请码列表
     * @param tInvitationCode 邀请码
     * @return 邀请码
     */
    @Override
    public List<TInvitationCode> findList(TInvitationCode tInvitationCode)
    {
        return super.findList(tInvitationCode);
    }

    /**
     * 分页查询邀请码列表
     * @param tInvitationCode 邀请码
     * @return 邀请码
     */
    @Override
    public PageInfo<TInvitationCode> findPage(TInvitationCode tInvitationCode)
    {
        return super.findPage(tInvitationCode);
    }

    /**
     * 保存邀请码
     * @param tInvitationCode
     * @return 结果
     */
    @Override
    public boolean save(TInvitationCode tInvitationCode)
    {
        checkCodeUnique(tInvitationCode);
        return super.save(tInvitationCode);
    }

    /**
     * 校验邀请码的唯一性
     * @param tInvitationCode
     */
    @Override
    public void checkCodeUnique(TInvitationCode tInvitationCode){
        TInvitationCode tInvitationCodeUnique = new TInvitationCode();
        tInvitationCodeUnique.setNotEqualId(tInvitationCode.getId());
        tInvitationCodeUnique.setCode(tInvitationCode.getCode());
        if (!CollectionUtils.isEmpty(mapper.findListWithUnique(tInvitationCodeUnique))) {
            throw new BizException("邀请码已存在，请重新输入！");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public TInvitationCode create() {
        String code = InviteCodeUtil.generateShortUuid();
        TInvitationCode tInvitationCode = new TInvitationCode();
        tInvitationCode.setCode(code);
        boolean exists = !CollectionUtils.isEmpty(mapper.findListWithUnique(tInvitationCode));
        while (exists) {
            code = InviteCodeUtil.generateShortUuid();
            exists = !CollectionUtils.isEmpty(mapper.findListWithUnique(tInvitationCode));
        }
        super.save(tInvitationCode);
        return tInvitationCode;
    }


    /**
     * 删除邀请码信息
     * @param tInvitationCode
     * @return 结果
     */
    @Override
    public boolean remove(TInvitationCode tInvitationCode)
    {
        return super.remove(tInvitationCode);
    }

    /**
     * 批量删除邀请码
     * @param ids 需要删除的邀请码ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTInvitationCodeByIds(String[] ids)
    {
        return mapper.deleteTInvitationCodeByIds(ids);
    }

}
