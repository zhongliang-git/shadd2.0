package com.aidex.shadd.site.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.site.domain.TSite;

/**
 * 站点Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
public interface TSiteService extends BaseService<TSite>
{

    /**
     * 批量删除站点
     * @param ids 需要删除的站点ID集合
     * @return 结果
     */
    public int deleteTSiteByIds(String[] ids);


    /**
    * 校验站点编码的唯一性
    * @param tSite 站点
    */
    public void checkCodeUnique(TSite tSite);

}
