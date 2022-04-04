package com.aidex.shadd.shipping.address.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import com.aidex.shadd.site.domain.TSite;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 配送服务子表对象 t_distribution_service
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
@Data
public class TDistributionService extends BaseEntity<TDistributionService>
{
    private static final long serialVersionUID = 1L;

    /** 站点Id */
    @Excel(name = "站点Id")
    private String site;

    /** 收货地址Id */
    private String receivingId;

    /** 配送费 */
    @Excel(name = "配送费")
    private BigDecimal shippingFee;

    /** 站点名称 */
    private String siteName;

    /** 站点集合 */
    private List<TSite> sites;
}
