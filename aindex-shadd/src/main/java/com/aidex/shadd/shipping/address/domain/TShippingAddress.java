package com.aidex.shadd.shipping.address.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 收货地址对象 t_shipping_address
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
@Data
public class TShippingAddress extends BaseEntity<TShippingAddress>
{
    private static final long serialVersionUID = 1L;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 区划 */
    @Excel(name = "区划")
    private String district;

    /** 维度 */
    private String latitude;

    /** 经度 */
    private String longitude;

    /** 详情地址 */
    @Excel(name = "详情地址")
    private String address;

    /** 区划编码 */
    private String districtCode;

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setDistrict(String district) 
    {
        this.district = district;
    }

    public String getDistrict() 
    {
        return district;
    }

    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }

    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setDistrictCode(String districtCode) 
    {
        this.districtCode = districtCode;
    }

    public String getDistrictCode() 
    {
        return districtCode;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("name", getName())
            .append("district", getDistrict())
            .append("latitude", getLatitude())
            .append("longitude", getLongitude())
            .append("address", getAddress())
            .append("districtCode", getDistrictCode())
            .append("remark", getRemark())
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createDept", getCreateDept())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("updateIp", getUpdateIp())
            .append("version", getVersion())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
