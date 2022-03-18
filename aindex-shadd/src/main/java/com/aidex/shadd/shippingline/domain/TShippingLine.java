package com.aidex.shadd.shippingline.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
/**
 * 运线对象 t_shipping_line
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-16
 */
@Data
public class TShippingLine extends BaseEntity<TShippingLine>
{
    private static final long serialVersionUID = 1L;

    /** 发货站点 */
    @Excel(name = "发货站点")
    @NotBlank(message = "发货站点不允许为空")
    private String shippingsiteid;

    /** 收货站点 */
    @Excel(name = "收货站点")
    @NotBlank(message = "收货站点不允许为空")
    private String seceivingsiteid;

    /** 服务类型 */
    @Excel(name = "服务类型", dictType = "server_type")
    @NotBlank(message = "服务类型不允许为空")
    private String servetype;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal freight;

    /** 发货点名称 */
    private String shippingSiteName;

    /** 收货点名称 */
    private String seceivingSiteName;

    /** 站线名称 */
    private String name;

    /** 站线编码 */
    private String code;
}
