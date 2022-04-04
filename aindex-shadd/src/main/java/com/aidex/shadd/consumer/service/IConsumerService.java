package com.aidex.shadd.consumer.service;

import com.aidex.shadd.consumer.domain.ConsumerModel;

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
}
