package com.aidex.shadd.saleproduct.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.saleproduct.domain.TRepertory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 库存子表Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
public interface TRepertoryMapper extends BaseMapper<TRepertory>
{

    /**
     * 批量删除库存子表
     * @param ids 需要删除的库存子表ID集合
     * @return
     */
    int deleteTRepertoryByIds(String[] ids);

    @Update({
            "UPDATE T_REPERTORY SET repertory=repertory+#{d.repertory}, total=total+ #{d.repertory}, "+
            "accountopen= accountopen + #{d.repertory} where spid = #{d.spid}"
    })
    int updateRepertory(@Param("d") TRepertory repertory);

    @Update({
            "UPDATE T_REPERTORY SET repertory=repertory-#{d.repertory},obligation=obligation+#{d.repertory} where spid = #{d.spid} and repertory-#{d.repertory} >= 0"
    })
    int updateProduct(@Param("d") TRepertory detail);
}
