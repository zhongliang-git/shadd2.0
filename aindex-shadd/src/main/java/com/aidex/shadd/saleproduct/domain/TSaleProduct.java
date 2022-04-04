package com.aidex.shadd.saleproduct.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
/**
 * 销售产品对象 t_sale_product
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
@Data
public class TSaleProduct extends BaseEntity<TSaleProduct>
{
    private static final long serialVersionUID = 1L;

    /** 销售站点 */
    @Excel(name = "销售站点")
    @NotBlank(message = "销售站点不允许为空")
    private String siteid;

    /** 产品ID */
    @Excel(name = "产品ID")
    @NotBlank(message = "产品ID不允许为空")
    private String productid;

    /** 售价 */
    @Excel(name = "售价")
    private BigDecimal price;

    private BigDecimal startPrice;

    private BigDecimal endPrice;

    /** 厂家名称 */
    private String factoryName;

    /** 图片 */
    private String image;

    /** 矿种 */
    private String minerals;

    /** 规格型号 */
    private String specification;

    /** 销售站点名称 */
    private String siteName;

    /** 售价 */
   private TRepertory repertory;

   /** 库存 */
   private BigDecimal repertoryNumber;

   /** 详情图 */
   private String imageDetailList;
}
