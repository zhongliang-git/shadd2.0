package com.aidex.shadd.site.service.impl;

import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.common.exception.BizException;
import com.aidex.shadd.site.domain.TSite;
import com.aidex.shadd.site.mapper.TSiteMapper;
import com.aidex.shadd.site.service.TSiteService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 站点Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
@Service
@Transactional(readOnly = true)
public class TSiteServiceImpl extends BaseServiceImpl<TSiteMapper, TSite> implements TSiteService
{
    private static final Logger log = LoggerFactory.getLogger(TSiteServiceImpl.class);

    /**
     * 获取单条数据
     * @param tSite 站点
     * @return 站点
     */
    @Override
    public TSite get(TSite tSite)
    {
        TSite dto = super.get(tSite);
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 站点id
     * @return 站点
     */
    @Override
    public TSite get(String id)
    {
        TSite dto = super.get(id);
        return dto;
    }


    /**
     * 查询站点列表
     * @param tSite 站点
     * @return 站点
     */
    @Override
    public List<TSite> findList(TSite tSite)
    {
        return super.findList(tSite);
    }

    /**
     * 分页查询站点列表
     * @param tSite 站点
     * @return 站点
     */
    @Override
    public PageInfo<TSite> findPage(TSite tSite)
    {
        return super.findPage(tSite);
    }

    /**
     * 保存站点
     * @param tSite
     * @return 结果
     */
    @Override
    public boolean save(TSite tSite)
    {
        checkCodeUnique(tSite);
        return super.save(tSite);
    }

    /**
     * 校验站点编码的唯一性
     * @param tSite
     */
    @Override
    public void checkCodeUnique(TSite tSite){
        TSite tSiteUnique = new TSite();
        tSiteUnique.setNotEqualId(tSite.getId());
        tSiteUnique.setCode(tSite.getCode());
        if (!CollectionUtils.isEmpty(mapper.findListWithUnique(tSiteUnique))) {
            throw new BizException("站点编码已存在，请重新输入！");
        }
    }


    /**
     * 删除站点信息
     * @param tSite
     * @return 结果
     */
    @Override
    public boolean remove(TSite tSite)
    {
        return super.remove(tSite);
    }

    /**
     * 批量删除站点
     * @param ids 需要删除的站点ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTSiteByIds(String[] ids)
    {
        return mapper.deleteTSiteByIds(ids);
    }

}
