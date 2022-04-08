package com.aidex.shadd.saleproduct.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 库存子表对象 t_repertory
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
@Data
public class TRepertory extends BaseEntity<TRepertory>
{
    private static final long serialVersionUID = 1L;

    /** 销售产品ID */
    private String spid;

    /** 库存 */
    @Excel(name = "库存")
    private BigDecimal repertory;

    /** 总量 */
    @Excel(name = "总量")
    private BigDecimal total;

    /** 已结算量 */
    @Excel(name = "已结算量")
    private BigDecimal clsd;

    /** 未结算量 */
    @Excel(name = "未结算量")
    private BigDecimal accountopen;

    /** 待结算 */
    @Excel(name = "待结算")
    private BigDecimal obligation;

    /** 总结算金额 */
    @Excel(name = "总结算金额")
    private BigDecimal totalAmount;

}
