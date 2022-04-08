package com.aidex.shadd.payrecord.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 充值记录对象 t_pay_record
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
@Data
public class TPayRecord extends BaseEntity<TPayRecord>
{
    private static final long serialVersionUID = 1L;

    /** 客户 */
    @Excel(name = "客户")
    private String consumerId;

    private String consumerIdName;
    /** 充值金额 */
    @Excel(name = "充值金额")
    private BigDecimal payMoney;

    /** 赠送金额 */
    @Excel(name = "赠送金额")
    private BigDecimal donateMoney;

    /** 客户 */
    @Excel(name = "客户名称")
    private String userName;

    /** 订单编号 */
    private String code;
}
