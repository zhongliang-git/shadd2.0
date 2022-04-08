package com.aidex.shadd.order.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单对象 t_order
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-07
 */
@Data
public class TOrder extends BaseEntity<TOrder>
{
    private static final long serialVersionUID = 1L;

    /** 客户 */
    @Excel(name = "客户")
    private String userId;

    private String userIdName;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private String shippingaddress;

    /** 配送日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "配送日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dispatchingdate;

    /** 配送日期开始 */
    private String beginDispatchingdate;

    /** 配送日期结束 */
    private String endDispatchingdate;
    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal total;

    /** 状态 */
    @Excel(name = "状态", dictType = "order_stauts")
    private String billStatus;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String code;

    /** 客户名称 */
    private String userName;

    /** 收货地址-详情*/
    private String shippingaddressName;

    /** 订单明细 */
    private List<TOrderDetail> orderDetailList;
}
