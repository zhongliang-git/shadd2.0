package com.aidex.shadd.domain;

import com.aidex.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.aidex.common.annotation.Excel;

import java.util.List;

/**
 * 厂家对象 t_factory
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-08
 */
@Data
public class TFactory extends BaseEntity<TFactory>
{
    private static final long serialVersionUID = 1L;

    /** 区划 */
    @Excel(name = "区划")
    private String district;

    /** 区划编码 */
    private String districtCode;

    /** 图片 */
    @Excel(name = "图片")
    private String image;

    /** 维度 */
    @Excel(name = "维度")
    private String latitude;

    /** 联系人 */
    @Excel(name = "联系人")
    private String linkman;

    /** 经度 */
    @Excel(name = "经度")
    private String longitude;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 厂家名称 */
    @Excel(name = "厂家名称")
    private String name;

    /** 详细地址 */
    private String address;

    /**
     * 采购产品集合
     */
    private List<Object> purchaseList;
}
