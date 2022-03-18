package com.aidex.shadd.product.domain;

import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aidex.common.annotation.Excel;
/**
 * 产品对象 t_product
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-10
 */
@Data
public class TProduct extends BaseEntity<TProduct>
{
    private static final long serialVersionUID = 1L;

    /** 矿种 */
    @Excel(name = "矿种")
    private String minerals;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String specification;

    public void setMinerals(String minerals) 
    {
        this.minerals = minerals;
    }

    public String getMinerals() 
    {
        return minerals;
    }

    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("minerals", getMinerals())
            .append("specification", getSpecification())
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
