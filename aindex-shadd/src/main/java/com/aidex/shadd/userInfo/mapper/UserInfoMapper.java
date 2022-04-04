package com.aidex.shadd.userInfo.mapper;

import com.aidex.common.core.mapper.BaseMapper;
import com.aidex.shadd.userInfo.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * 用户信息Mapper接口
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
public interface UserInfoMapper extends BaseMapper<UserInfo>
{

    /**
     * 批量删除用户信息
     * @param ids 需要删除的用户信息ID集合
     * @return
     */
    int deleteUserInfoByIds(String[] ids);

    /**
     * 更新用户余额信息
     * @param userId 用户Id
     * @param money 金额
     * @return
     */
    @Update({
            "UPDATE user_info SET balance = balance + #{money}, total = total +  #{money} " +
            "WHERE user_id = #{userId}"
    })
    int updateBalanceAdd(@Param("userId") String userId,@Param("money") BigDecimal money);
}
