package com.aidex.shadd.releaserecords.domain;

import com.aidex.common.annotation.Excel;
import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
/**
 * 发货记录对象 t_release_records
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-16
 */
@Data
public class TReleaseRecords extends BaseEntity<TReleaseRecords>
{
    private static final long serialVersionUID = 1L;

    /** 厂家 */
    @Excel(name = "厂家")
    @NotBlank(message = "厂家不允许为空")
    private String dactoryid;

    /**
     * 厂家名称
     */
    private String factoryName;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 站点名称
     */
    private String siteName;

    /** 产品 */
    @Excel(name = "产品")
    @NotBlank(message = "产品不允许为空")
    private String productid;

    /** 收货站点 */
    @Excel(name = "收货站点")
    @NotBlank(message = "收货站点不允许为空")
    private String siteid;

    /** 收货数量 */
    @Excel(name = "收货数量")
    private Long receivingnumber;

}
