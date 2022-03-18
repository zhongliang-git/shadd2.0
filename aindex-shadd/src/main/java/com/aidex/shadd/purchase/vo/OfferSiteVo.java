package com.aidex.shadd.purchase.vo;

import lombok.Data;

/**
 * 报价站点VO模型
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
@Data
public class OfferSiteVo {
    private static final long serialVersionUID = 1L;
    /**
     * 站点Id
     */
    private String siteId;

    /**
     * 站点名称
     */
    private String siteName;
}
