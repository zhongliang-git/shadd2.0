package com.aidex.shadd.order.controller;

import java.util.List;
import com.aidex.common.annotation.Log;
import com.aidex.common.core.domain.R;
import com.github.pagehelper.PageInfo;
import com.aidex.common.core.page.PageDomain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import javax.validation.constraints.*;
import org.springframework.web.bind.annotation.*;
import com.aidex.common.core.controller.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import com.aidex.shadd.order.domain.TOrder;
import com.aidex.shadd.order.service.TOrderService;

/**
 * 订单Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-07
 */
@RestController
@RequestMapping("/shadd/tOrder")
public class TOrderController extends BaseController
{
    @Autowired
    private TOrderService tOrderService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrder:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TOrder tOrder, HttpServletRequest request, HttpServletResponse response)
    {
        tOrder.setPage(new PageDomain(request, response));
        return R.data(tOrderService.findPage(tOrder));
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrder:query')")
    @GetMapping(value = "/{id}")
    public R<TOrder> detail(@PathVariable("id") String id)
    {
        return R.data(tOrderService.get(id));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrder:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TOrder tOrder)
    {
        return R.status(tOrderService.save(tOrder));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrder:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TOrder tOrder)
    {
        return R.status(tOrderService.save(tOrder));
    }


    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrder:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tOrderService.deleteTOrderByIds(ids));
    }


    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrder:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TOrder tOrder)
    {
        List<TOrder> list = tOrderService.findList(tOrder);
        ExcelUtil<TOrder> util = new ExcelUtil<TOrder>(TOrder.class);
        return util.exportExcel(list, "订单数据");
    }

}
