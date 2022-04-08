package com.aidex.shadd.consumer.mapper;

import com.aidex.shadd.consumer.domain.ConsumeRecordModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhongliang
 * @version 1.0
 * @description: 客户数据层
 * @date 2022/4/4 23:30
 */
@Repository
public interface IConsumerMapper {

    /**
     * 用户手机号更新
     * @param: userId 用户Id
     * @param: phoneNumber 手机号
     * @author zhongliang
     * @date: 2022/4/4 23:33
     */
    @Update({
            "UPDATE SYS_USER SET phonenumber = #{phoneNumber} "+
            "WHERE ID = #{userId}"
    })
    int updateUserPhoneNumberBYId(@Param("userId") String userId,
                                  @Param("phoneNumber") String phoneNumber);

    /**
     * 查询消费记录,根据用户
     * @param: consumeRecordModel 查询条件
     * @author zhongliang
     * @date: 2022/4/5 23:43
     */
    List<ConsumeRecordModel> queryConsumeRecord(ConsumeRecordModel consumeRecordModel);
}
