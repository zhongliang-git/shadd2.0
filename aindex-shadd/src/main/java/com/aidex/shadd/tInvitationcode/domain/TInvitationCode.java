package com.aidex.shadd.tInvitationcode.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import com.aidex.common.utils.log.annotation.FieldRemark;
import com.aidex.common.utils.log.annotation.LogField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
/**
 * 邀请码对象 t_invitation_code
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
@Data
public class TInvitationCode extends BaseEntity<TInvitationCode>
{
    private static final long serialVersionUID = 1L;

    /** 邀请码 */
    @Excel(name = "邀请码")
    @NotBlank(message = "邀请码不允许为空")
    @LogField
    @FieldRemark(name = "邀请码",field = "code")
    private String code;

    /** 客户 */
    @Excel(name = "客户")
    private String userid;

    /** 客户 */
    @Excel(name = "客户名称")
    private String userName;

}
