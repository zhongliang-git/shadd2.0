package com.aidex.shadd.saleproduct.service;

import com.aidex.common.core.service.BaseService;
import com.aidex.shadd.saleproduct.domain.TRepertory;

/**
 * 库存子表Service接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
public interface TRepertoryService extends BaseService<TRepertory>
{
    String REPERTORY_KEY = "REPERTORY:";
    /**
     * 批量删除库存子表
     * @param ids 需要删除的库存子表ID集合
     * @return 结果
     */
     int deleteTRepertoryByIds(String[] ids);

    /**
     * 新增库存
     * @param repertory 库存
     * @return 结果
     */
    boolean saveByRepertoryNumber(TRepertory repertory);

    /**
     * 修改库存
     * @param repertory 库存
     * @return 结果
     */
    boolean modifyRepertoryNumber(TRepertory repertory);

    /**
     * 库存销货
     * @param: repertory 库存信息
     * @author zhongliang
     * @date: 2022/4/7 12:37
     */
    boolean repertorySales(TRepertory repertory);
}
