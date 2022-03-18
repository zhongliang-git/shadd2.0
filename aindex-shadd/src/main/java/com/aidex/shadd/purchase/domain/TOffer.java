package com.aidex.shadd.purchase.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import com.aidex.shadd.site.domain.TSite;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 厂家报价子表对象 t_offer
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
@Data
public class TOffer extends BaseEntity<TOffer>
{
    private static final long serialVersionUID = 1L;

    /** 发货站点 */
    @Excel(name = "发货站点")
    private String siteid;

    private List<TSite> sites;

    /** 采购价 */
    @Excel(name = "采购价")
    private BigDecimal price;

    /** 采购产品Id */
    private String purchaseId;

    public void setIsNewRecord(boolean isNewRecord) {
        super.isNewRecord = isNewRecord;
    }
}
