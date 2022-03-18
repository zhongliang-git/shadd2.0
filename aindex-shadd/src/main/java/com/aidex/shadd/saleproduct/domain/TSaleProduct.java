package com.aidex.shadd.saleproduct.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    public void setSiteid(String siteid) 
    {
        this.siteid = siteid;
    }

    public String getSiteid() 
    {
        return siteid;
    }

    public void setProductid(String productid) 
    {
        this.productid = productid;
    }

    public String getProductid() 
    {
        return productid;
    }

    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("siteid", getSiteid())
            .append("productid", getProductid())
            .append("price", getPrice())
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
