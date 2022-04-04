package com.aidex.shadd.tInvitationcode.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.tInvitationcode.domain.TInvitationCode;

/**
 * 邀请码Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
public interface TInvitationCodeMapper extends BaseMapper<TInvitationCode>
{

    /**
     * 批量删除邀请码
     * @param ids 需要删除的邀请码ID集合
     * @return
     */
    public int deleteTInvitationCodeByIds(String[] ids);


}
