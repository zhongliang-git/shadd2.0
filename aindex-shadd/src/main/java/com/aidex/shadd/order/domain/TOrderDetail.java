package com.aidex.shadd.order.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 订单明细子表对象 t_order_detail
 * @author ${functionAuthor}
 * @email ${functionAuthorEmail}
 * @date 2022-04-07
 */
@Data
public class TOrderDetail extends BaseEntity<TOrderDetail>
{
    private static final long serialVersionUID = 1L;

    /** 销售产品 */
    @Excel(name = "销售产品")
    private String productId;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 配送费 */
    @Excel(name = "配送费")
    private BigDecimal shippingFee;

    /** 数量 */
    @Excel(name = "数量")
    private BigDecimal num;

    /** 小计 */
    @Excel(name = "小计")
    private BigDecimal total;

    /** 订单Id */
    private String orderId;

    /** 产品名称*/
    private String productName;
}
