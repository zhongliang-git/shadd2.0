package com.aidex.shadd.consumer.service;

import com.aidex.shadd.consumer.domain.ConsumeRecordModel;
import com.aidex.shadd.consumer.domain.ConsumerModel;

import java.util.List;

/**
 * @author zhongliang
 * @version 1.0
 * @description: 客户服务接口
 * @date 2022/4/3 21:21
 */
public interface IConsumerService {

    /**
     * 客户注册
     * @param consumerModel 用户信息
     * @return: com.aidex.shadd.consumer.domain.ConsumerModel 用户信息
     * @author zhongliang
     * @date: 2022/4/4 0:05
     */
    ConsumerModel register(ConsumerModel consumerModel);
    
    /** 
     * 修改手机号
     * @param: userId 用户Id
     * @param: phone 手机号
     * @author zhongliang
     * @date: 2022/4/5 0:49
     */ 
    boolean modifyUserPhoneNumber(String userId, String phone);

    /**
     * 查询消费记录
     * @param: consumeRecordModel 查询条件
     * @author zhongliang
     * @date: 2022/4/6 10:28
     */
    List<ConsumeRecordModel> findConsumeRecord(ConsumeRecordModel consumeRecordModel);
}
