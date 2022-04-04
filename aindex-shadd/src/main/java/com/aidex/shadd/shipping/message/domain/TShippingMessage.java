package com.aidex.shadd.shipping.message.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import com.aidex.shadd.shipping.address.domain.TShippingAddress;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 收货信息对象 t_shipping_message
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
@Data
public class TShippingMessage extends BaseEntity<TShippingMessage>
{
    private static final long serialVersionUID = 1L;

    /** 区划 */
    @Excel(name = "区划")
    @NotBlank(message = "区划不允许为空")
    private String district;

    /** 维度 */
    private String latitude;

    /** 经度 */
    private String longitude;

    /** 详情地址 */
    @Excel(name = "详情地址")
    @NotBlank(message = "详情地址不允许为空")
    private String address;

    /** 客户名称 */
    @Excel(name = "客户名称")
    @NotBlank(message = "客户名称不允许为空")
    private String userid;

    private String useridName;
    /** 收货人电话 */
    @Excel(name = "收货人电话")
    @NotBlank(message = "收货人电话不允许为空")
    private String phone;

    /** 收货单位 */
    @Excel(name = "收货单位")
    @NotBlank(message = "收货单位不允许为空")
    private String consignee;

    /** 区划编码 */
    private String districtCode;

    /** 客户名称 */
    private String userName;

    /** 收货地址ID */
    private String addressId;

    /** 收货地址 */
    private List<TShippingAddress> addressList;
}
