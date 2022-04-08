package com.aidex.shadd.order.service.impl;

import com.aidex.common.core.domain.entity.SysUser;
import com.aidex.common.core.redis.RedisLock;
import com.aidex.common.core.service.BaseServiceImpl;
import com.aidex.common.exception.CustomException;
import com.aidex.common.exception.SysException;
import com.aidex.framework.cache.UserUtils;
import com.aidex.shadd.order.domain.TOrder;
import com.aidex.shadd.order.domain.TOrderDetail;
import com.aidex.shadd.order.mapper.TOrderDetailMapper;
import com.aidex.shadd.order.mapper.TOrderMapper;
import com.aidex.shadd.order.service.TOrderDetailService;
import com.aidex.shadd.order.service.TOrderService;
import com.aidex.shadd.userInfo.service.UserInfoService;
import com.aidex.shadd.util.CodeUtil;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单Service业务层处理
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-07
 */
@Service
@Transactional(readOnly = true)
public class TOrderServiceImpl extends BaseServiceImpl<TOrderMapper, TOrder> implements TOrderService
{
    private static final Logger log = LoggerFactory.getLogger(TOrderServiceImpl.class);
    private static final String USER_OTHER = "USER_OTHER:";

    @Autowired(required = false)
        private TOrderDetailMapper tOrderDetailMapper;

    /**
     * 获取单条数据
     * @param tOrder 订单
     * @return 订单
     */
    @Override
    public TOrder get(TOrder tOrder)
    {
        TOrder dto = super.get(tOrder);
                SysUser sysUser = UserUtils.getSysUser(dto.getUserId());
            dto.setUserIdName(sysUser.getName());
        return dto;
    }

    /**
     * 获取单条数据
     * @param id 订单id
     * @return 订单
     */
    @Override
    public TOrder get(String id)
    {
        TOrder dto = super.get(id);
                SysUser sysUser = UserUtils.getSysUser(dto.getUserId());
                dto.setUserIdName(sysUser.getName());
        return dto;
    }


    /**
     * 查询订单列表
     * @param tOrder 订单
     * @return 订单
     */
    @Override
    public List<TOrder> findList(TOrder tOrder)
    {
        return super.findList(tOrder);
    }

    /**
     * 分页查询订单列表
     * @param tOrder 订单
     * @return 订单
     */
    @Override
    public PageInfo<TOrder> findPage(TOrder tOrder)
    {
        return super.findPage(tOrder);
    }

    /**
     * 保存订单
     * @param tOrder
     * @return 结果
     */
    @Override
    public boolean save(TOrder tOrder)
    {
        return super.save(tOrder);
    }


    /**
     * 删除订单信息
     * @param tOrder
     * @return 结果
     */
    @Override
    public boolean remove(TOrder tOrder)
    {
        return super.remove(tOrder);
    }

    /**
     * 批量删除订单
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    @Transactional(readOnly = false)
    @Override
    public int deleteTOrderByIds(String[] ids)
    {
        for (String id : ids)
        {
            TOrderDetail tOrderDetail = new TOrderDetail();
            tOrderDetail.setOrderId(id);
            List<TOrderDetail> tOrderDetailList = tOrderDetailMapper.findList(tOrderDetail);
            if (tOrderDetailList.size() > 0)
            {
                throw new CustomException("子表存在数据,不能删除");
            }
        }
        return mapper.deleteTOrderByIds(ids);
    }
    @Autowired
    TOrderDetailService orderDetailService;
    @Autowired
    UserInfoService userInfoService;

    @Override
    @Transactional(readOnly = false)
    public void other(TOrder order) {
        List<TOrderDetail> orderDetailList = order.getOrderDetailList();
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SysException("未选择任何商品，请先选择商品进行下单");
        }
        // 一个用户只允许一个进程进行下单，防止重复下单、刷单行为
        String userId = order.getUserId();
        String key = this.getLock(userId);
        try {
            // 下单校验
            this.otherVerify(order);
            // 扣减用户余额
            userInfoService.deductionBalance(order.getUserId(), order.getTotal());
            // 生成订单编号
            String orderCode = CodeUtil.generateOrderCode();
            order.setCode(orderCode);
            // 生成订单
            order.setBillStatus("未完成");
            super.save(order);
            // 赋值订单Id
            orderDetailList.forEach(orderDetail -> orderDetail.setOrderId(order.getId()));
            // 生成订单明细
            orderDetailService.generateOrderDetail(orderDetailList);
        } finally {
            redisLock.unlock(key);
        }

    }

    /**
     * 下单校验
     * @param: order 订单信息
     * @author zhongliang
     * @date: 2022/4/7 15:46
     */
    private void otherVerify(TOrder order) {
        List<TOrderDetail> orderDetailList = order.getOrderDetailList();
        BigDecimal totalMoney = new BigDecimal(0);
        List<TOrderDetail> newOrderDetailList = new ArrayList<>(orderDetailList.size());
        for (TOrderDetail orderDetail : orderDetailList) {
            BigDecimal subtotal = orderDetail.getTotal();
            BigDecimal num = orderDetail.getNum();
            // 去除购货数量为空/为0的数据
            if (num == null || num.equals(new BigDecimal(0)) ||
                    subtotal == null ||
                    subtotal.equals(new BigDecimal(0))) {
                continue;
            }
            // 校验小计金额是否 = 数量*(配送费+金额),如果否，小计金额 = 数量*(配送费+金额)
            BigDecimal price = orderDetail.getShippingFee().add(orderDetail.getPrice());
            BigDecimal totalPrice = num.multiply(price);
            if (!subtotal.equals(totalPrice)) {
                subtotal = totalPrice;
                orderDetail.setTotal(totalPrice);
            }
            newOrderDetailList.add(orderDetail);
            totalMoney = totalMoney.add(subtotal);
        }
        if (CollectionUtils.isEmpty(newOrderDetailList)) {
            throw new SysException("至少选择一件，且购买数据大于0的商品");
        }
        // 判断订单总额与订单明细小计金额是否一致，如果否，将小计金额汇总给订单金额
        BigDecimal total = order.getTotal();
        if (!totalMoney.equals(total)) {
            order.setTotal(totalMoney);
        }
        order.setOrderDetailList(newOrderDetailList);
    }

    @Autowired
    RedisLock redisLock;

    @SneakyThrows
    private String getLock(String userId) {
        String key = USER_OTHER + userId;
        int i = 0;
        // 6次未获取到锁，不再获取
        boolean lock = redisLock.lock(key);
        while (i < 6 && !lock) {
            Thread.currentThread().sleep(1000);
            i ++;
            lock = redisLock.lock(key);
        }
        if (!lock) {
            throw new SysException("您有一个订单正在下单中，未防止重复下单，请稍后再试!");
        }
        return key;
    }
}
