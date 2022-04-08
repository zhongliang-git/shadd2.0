package com.aidex.shadd.consumer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhongliang
 * @version 1.0
 * @description: 消费记录实体类
 * @date 2022/4/5 23:27
 */
@Data
public class ConsumeRecordModel {

    /** id **/
    private String id;

    /** 单据编号 */
    private String code;

    /** 金额 */
    private BigDecimal money;

    /** 创建日期 */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date createTime;

    /** 用户Id */
    private String userId;

    /** 类型 */
    private String type;

    /** 类型集 */
    private List<String> types;
}
