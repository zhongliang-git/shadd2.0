package com.aidex.shadd.releaserecords.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;
/**
 * 货物运输子表对象 t_goods_transport
 * @author ${functionAuthor}
 * @email ${functionAuthorEmail}
 * @date 2022-03-16
 */
@Data
public class TGoodsTransport extends BaseEntity<TGoodsTransport>
{
    private static final long serialVersionUID = 1L;

    /** 发货记录Id */
    @Excel(name = "发货记录Id")
    private String relid;

    /** 到货数量 */
    @Excel(name = "到货数量")
    private Long number;

    /** 铁路运线 */
    @Excel(name = "铁路运线")
    private String lineid;

    /** 铁路运线名称 */
    private String lineName;
    /** 单据状态 */
    @Excel(name = "单据状态")
    private String billstatus;
}
