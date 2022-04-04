package com.aidex.shadd.tInvitationcode.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.tInvitationcode.domain.TInvitationCode;

/**
 * 邀请码Service接口
 *
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
public interface TInvitationCodeService extends BaseService<TInvitationCode> {

    /**
     * 批量删除邀请码
     *
     * @param ids 需要删除的邀请码ID集合
     * @return 结果
     */
    int deleteTInvitationCodeByIds(String[] ids);


    /**
     * 校验邀请码的唯一性
     *
     * @param tInvitationCode 邀请码
     */
    void checkCodeUnique(TInvitationCode tInvitationCode);

    /**
     * 生成邀请码
     */
    TInvitationCode create();
}
