package com.aidex.shadd.site.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
/**
 * 站点对象 t_site
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
@Data
public class TSite extends BaseEntity<TSite>
{
    private static final long serialVersionUID = 1L;

    /** 站点名称 */
    @Excel(name = "站点名称")
    @NotBlank(message = "站点名称不允许为空")
    private String name;

    /** 站点编码 */
    @Excel(name = "站点编码")
    @NotBlank(message = "站点编码不允许为空")
    private String code;

    /** 经度 */
    private String longitude;

    /** 纬度 */
    private String latitude;

    /** 区划 */
    @Excel(name = "区划")
    @NotBlank(message = "区划不允许为空")
    private String zoning;

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }

    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
    }

    public void setZoning(String zoning) 
    {
        this.zoning = zoning;
    }

    public String getZoning() 
    {
        return zoning;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("name", getName())
            .append("code", getCode())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("zoning", getZoning())
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
