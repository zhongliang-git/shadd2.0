package com.aidex.shadd.consumer.controller;

import com.aidex.common.core.domain.AjaxResult;
import com.aidex.shadd.shipping.message.domain.TShippingMessage;
import com.aidex.shadd.shipping.message.service.TShippingMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongliang
 * @version 1.0
 * @description: 收货地址接口
 * @date 2022/4/5 2:27
 */
@RestController
@RequestMapping("/shadd/consumer/address")
public class ReceivingAddressController {
    @Autowired
    private TShippingMessageService tShippingMessageService;

    @PostMapping("/list")
    public AjaxResult list(@RequestParam String userId)
    {
        TShippingMessage tShippingMessage = new TShippingMessage();
        tShippingMessage.setUserid(userId);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", tShippingMessageService.findList(tShippingMessage));
        return ajaxResult;
    }

}
