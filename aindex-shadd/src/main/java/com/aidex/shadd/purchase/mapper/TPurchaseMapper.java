package com.aidex.shadd.purchase.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.purchase.domain.TPurchase;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 采购产品Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
@Repository
public interface TPurchaseMapper extends BaseMapper<TPurchase>
{

    /**
     * 批量删除采购产品
     * @param ids 需要删除的采购产品ID集合
     * @return
     */
     int deleteTPurchaseByIds(String[] ids);

    /**
     * 查询采购产品，根据厂家Id
     * @param factoryId 厂家Id
     * @return
     */
     List<TPurchase> queryListByFactory(String factoryId);

     @Delete({
             "DELETE FROM T_PURCHASE WHERE factoryid = #{factoryId}"
     })
    int deleteByFactoryId(@Param("factoryId") String factoryId);

    /**
     * 查询采购产品 根据产品名称 + 厂家Id
     * @param tPurchase 查询条件
     * @return
     */
    List<TPurchase> findPurchaseList(TPurchase tPurchase);
}
