package com.aidex.shadd.userInfo.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 用户信息对象 user_info
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
@Data
public class UserInfo extends BaseEntity<UserInfo>
{
    private static final long serialVersionUID = 1L;

    /** 用户余额 */
    @Excel(name = "用户余额")
    private BigDecimal balance;

    /** 用户总额 */
    @Excel(name = "用户总额")
    private BigDecimal total;

    /** 客户 */
    @Excel(name = "客户")
    private String userId;

    private String userIdName;
    /** 积分 */
    @Excel(name = "积分")
    private Long integrate;

    /** 客户 */
    @Excel(name = "客户名称")
    private String userName;
}
