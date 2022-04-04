package com.aidex.shadd.consumer.controller;

import com.aidex.common.constant.Constants;
import com.aidex.common.core.domain.AjaxResult;
import com.aidex.common.core.domain.entity.SysUser;
import com.aidex.common.utils.SecurityUtils;
import com.aidex.framework.web.service.SysLoginService;
import com.aidex.shadd.consumer.domain.ConsumerModel;
import com.aidex.shadd.consumer.service.IConsumerService;
import com.aidex.shadd.userInfo.domain.UserInfo;
import com.aidex.shadd.userInfo.service.UserInfoService;
import com.aidex.shadd.util.StaticPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 客户Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-28
 */
@RestController
@RequestMapping("/shadd/consumer")
public class ConsumerController {
    @Autowired
    IConsumerService consumerService;
    @Autowired
    SysLoginService loginService;
    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/userInfo")
    public AjaxResult userInfo(){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        AjaxResult ajax = AjaxResult.success();
        ConsumerModel consumerModel = new ConsumerModel();
        consumerModel.setUser(user);
        // 查询用户余额、积分等信息
        UserInfo userInfo =  userInfoService.findUserInfo(user.getId());
        consumerModel.setUserInfo(userInfo);
        ajax.put("user", consumerModel);
        return ajax;
    }

    @PostMapping("registration")
    public AjaxResult userRegistration(@RequestBody ConsumerModel consumerModel) {
        // 调用注册方法
        consumerModel = consumerService.register(consumerModel);
        return this.login(consumerModel.getUser().getUserName());
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestParam String userName){
        // 模拟登录 - 生成token
        String token = loginService.login(userName, StaticPoolUtil.INIT_PASS);
        AjaxResult ajax = AjaxResult.success();
        ajax.put(Constants.TOKEN, token);
        // 获取用户信息
        return ajax;
    }
}
