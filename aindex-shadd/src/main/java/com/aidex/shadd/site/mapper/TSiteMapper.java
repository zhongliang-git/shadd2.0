package com.aidex.shadd.site.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.site.domain.TSite;

import java.util.List;

/**
 * 站点Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
public interface TSiteMapper extends BaseMapper<TSite>
{

    /**
     * 批量删除站点
     * @param ids 需要删除的站点ID集合
     * @return
     */
     int deleteTSiteByIds(String[] ids);

    /**
     * 根据Id查询
     * @param id 站点Id
     * @return
     */
     List<TSite> querySiteById(String id);
}
