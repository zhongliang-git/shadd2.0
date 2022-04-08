package com.aidex.shadd.consumer.controller;

import com.aidex.common.constant.Constants;
import com.aidex.common.core.domain.AjaxResult;
import com.aidex.common.core.domain.entity.SysUser;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.utils.SecurityUtils;
import com.aidex.framework.web.service.SysLoginService;
import com.aidex.shadd.consumer.domain.ConsumeRecordModel;
import com.aidex.shadd.consumer.domain.ConsumerModel;
import com.aidex.shadd.consumer.service.IConsumerService;
import com.aidex.shadd.order.domain.TOrder;
import com.aidex.shadd.order.domain.TOrderDetail;
import com.aidex.shadd.order.service.TOrderDetailService;
import com.aidex.shadd.order.service.TOrderService;
import com.aidex.shadd.userInfo.domain.UserInfo;
import com.aidex.shadd.userInfo.service.UserInfoService;
import com.aidex.shadd.util.StaticPoolUtil;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @PostMapping("/modifyPhoneNumber")
    public AjaxResult modifyPhoneNumber(@RequestParam("userId") String userId, @RequestParam("phone") String phone) {
        AjaxResult ajax = AjaxResult.success();
        // 修改手机号
        boolean succeed = consumerService.modifyUserPhoneNumber(userId, phone);
        if (succeed) {
            return AjaxResult.success();
        }
        return AjaxResult.error("修改失败,请微信联系客服!");
    }

    @PostMapping("/consumeRecord")
    public AjaxResult consumeRecord(@RequestBody ConsumeRecordModel consumeRecordModel){
        AjaxResult ajaxResult = AjaxResult.success();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        consumeRecordModel.setUserId(user.getId());
        List<ConsumeRecordModel> consumeRecordModels = consumerService.findConsumeRecord(consumeRecordModel);
        ajaxResult.put("records", consumeRecordModels);
        return ajaxResult;
    }
    @Autowired
    TOrderService orderService;

    @PostMapping("/orderList")
    public AjaxResult orderList(@RequestBody TOrder tOrder, HttpServletRequest request, HttpServletResponse response)
    {
        tOrder.setPage(new PageDomain(request, response));
        AjaxResult ajaxResult = AjaxResult.success();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        tOrder.setUserId(user.getId());
        PageInfo<TOrder> orders = orderService.findPage(tOrder);
        ajaxResult.put("orderInfo", orders);
        // 订单查询
        return ajaxResult;
    }

    @PostMapping("/other")
    public AjaxResult other(@RequestBody TOrder order) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        order.setUserId(user.getId());
        orderService.other(order);
        return AjaxResult.success();
    }
    @Autowired
    TOrderDetailService orderDetailService;

    @GetMapping("/orderDetail")
    public AjaxResult orderDetail(@Param("orderId") String orderId){
        List<TOrderDetail> orderDetails = orderDetailService.findOrderDetail(orderId);
        return AjaxResult.success(orderDetails);
    }

    @GetMapping("/order")
    public AjaxResult order(@Param("orderId") String orderId){
        TOrder tOrder = orderService.get(orderId);
        return AjaxResult.success(tOrder);
    }
}
