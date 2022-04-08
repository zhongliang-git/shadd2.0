package com.aidex.shadd.consumer.enums;

/**
 * @author zhongliang
 * @version 1.0
 * @description: 账单类型
 * @date 2022/4/5 23:37
 */
public enum BillTypeEnum {

    /**
     * 充值
     */
    PAY("pay"),

    /**
     * 订单
     */
    ORDER("order");

    private String code;

    BillTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
