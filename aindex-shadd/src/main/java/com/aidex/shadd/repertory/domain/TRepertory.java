package com.aidex.shadd.repertory.domain;

import java.math.BigDecimal;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aidex.common.utils.log.annotation.FieldRemark;
import com.aidex.common.utils.log.annotation.LogField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.aidex.common.annotation.Excel;
/**
 * 库存对象 t_repertory
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
@Data
public class TRepertory extends BaseEntity<TRepertory>
{
    private static final long serialVersionUID = 1L;

    /** 销售产品ID */
    private String spid;

    /** 库存 */
    @Excel(name = "库存")
    private Long repertory;

    /** 总量 */
    @Excel(name = "总量")
    private Long total;

    /** 已结算量 */
    @Excel(name = "已结算量")
    private Long clsd;

    /** 未结算量 */
    @Excel(name = "未结算量")
    private Long accountopen;

    /** 待结算 */
    @Excel(name = "待结算")
    private Long obligation;

    /** 总结算金额 */
    @Excel(name = "总结算金额")
    private BigDecimal totalAmount;

    public void setSpid(String spid) 
    {
        this.spid = spid;
    }

    public String getSpid() 
    {
        return spid;
    }

    public void setRepertory(Long repertory) 
    {
        this.repertory = repertory;
    }

    public Long getRepertory() 
    {
        return repertory;
    }

    public void setTotal(Long total) 
    {
        this.total = total;
    }

    public Long getTotal() 
    {
        return total;
    }

    public void setClsd(Long clsd) 
    {
        this.clsd = clsd;
    }

    public Long getClsd() 
    {
        return clsd;
    }

    public void setAccountopen(Long accountopen) 
    {
        this.accountopen = accountopen;
    }

    public Long getAccountopen() 
    {
        return accountopen;
    }

    public void setObligation(Long obligation) 
    {
        this.obligation = obligation;
    }

    public Long getObligation() 
    {
        return obligation;
    }

    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("spid", getSpid())
            .append("repertory", getRepertory())
            .append("total", getTotal())
            .append("clsd", getClsd())
            .append("accountopen", getAccountopen())
            .append("obligation", getObligation())
            .append("totalAmount", getTotalAmount())
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
