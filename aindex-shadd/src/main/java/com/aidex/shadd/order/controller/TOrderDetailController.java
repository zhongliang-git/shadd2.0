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
import com.aidex.shadd.order.domain.TOrderDetail;
import com.aidex.shadd.order.service.TOrderDetailService;

/**
 * 订单明细子表Controller
 * @author ${functionAuthor}
 * @email ${functionAuthorEmail}
 * @date 2022-04-07
 */
@RestController
@RequestMapping("/shadd/tOrderDetail")
public class TOrderDetailController extends BaseController
{
    @Autowired
    private TOrderDetailService tOrderDetailService;

    /**
     * 查询订单明细子表列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrderDetail:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TOrderDetail tOrderDetail, HttpServletRequest request, HttpServletResponse response)
    {
        tOrderDetail.setPage(new PageDomain(request, response));
        return R.data(tOrderDetailService.findPage(tOrderDetail));
    }

    /**
     * 获取订单明细子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrderDetail:query')")
    @GetMapping(value = "/{id}")
    public R<TOrderDetail> detail(@PathVariable("id") String id)
    {
        return R.data(tOrderDetailService.get(id));
    }

    /**
     * 新增订单明细子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrderDetail:add')")
    @Log(title = "订单明细子表", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TOrderDetail tOrderDetail)
    {
        return R.status(tOrderDetailService.save(tOrderDetail));
    }

    /**
     * 修改订单明细子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrderDetail:edit')")
    @Log(title = "订单明细子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TOrderDetail tOrderDetail)
    {
        return R.status(tOrderDetailService.save(tOrderDetail));
    }


    /**
     * 删除订单明细子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrderDetail:remove')")
    @Log(title = "订单明细子表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tOrderDetailService.deleteTOrderDetailByIds(ids));
    }


    /**
     * 导出订单明细子表列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOrderDetail:export')")
    @Log(title = "订单明细子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TOrderDetail tOrderDetail)
    {
        List<TOrderDetail> list = tOrderDetailService.findList(tOrderDetail);
        ExcelUtil<TOrderDetail> util = new ExcelUtil<TOrderDetail>(TOrderDetail.class);
        return util.exportExcel(list, "订单明细子表数据");
    }

}
