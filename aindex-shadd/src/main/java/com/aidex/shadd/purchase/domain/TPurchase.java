package com.aidex.shadd.purchase.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import com.aidex.common.utils.log.annotation.FieldRemark;
import com.aidex.common.utils.log.annotation.LogField;
import com.aidex.shadd.product.domain.TProduct;
import com.aidex.shadd.purchase.vo.OfferSiteVo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

/**
 * 采购产品对象 t_purchase
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
@Data
public class TPurchase extends BaseEntity<TPurchase>
{
    private static final long serialVersionUID = 1L;

    /** 主图 */
    @Excel(name = "主图")
    @NotBlank(message = "主图不允许为空")
    @LogField
    @FieldRemark(name = "主图",field = "image")
    private String image;

    /** 厂家 */
    @Excel(name = "厂家")
    @NotBlank(message = "厂家不允许为空")
    @LogField
    @FieldRemark(name = "厂家",field = "factoryid")
    private String factoryid;

    /** 产品 */
    @Excel(name = "产品")
    @NotBlank(message = "产品不允许为空")
    @LogField
    @FieldRemark(name = "产品",field = "productid")
    private String productid;

    /** 产品对象 */
    private TProduct product;

    /** 出厂价 */
    @Excel(name = "出厂价")
    @LogField
    @FieldRemark(name = "出厂价",field = "exw")
    private BigDecimal exw;

    /**
     * 采购价对象
     */
    private List<TOffer> offers;

    /**
     * 详情图集合 json
     */
    private String imageDetailList;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 采购集合
     */
    private List<OfferSiteVo> offerList;

    public void setIsNewRecord(boolean isNewRecord) {
        super.isNewRecord = isNewRecord;
    }
}
