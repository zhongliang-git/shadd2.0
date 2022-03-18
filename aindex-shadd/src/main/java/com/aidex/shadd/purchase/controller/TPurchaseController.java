package com.aidex.shadd.purchase.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.purchase.domain.TPurchase;
import com.aidex.shadd.purchase.service.TPurchaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 采购产品Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
@RestController
@RequestMapping("/shadd/purchaseList")
public class TPurchaseController extends BaseController
{
    @Autowired
    private TPurchaseService tPurchaseService;

    /**
     * 查询采购产品列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPurchase:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TPurchase tPurchase, HttpServletRequest request, HttpServletResponse response)
    {
        tPurchase.setPage(new PageDomain(request, response));
        return R.data(tPurchaseService.findPage(tPurchase));
    }

    /**
     * 获取采购产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPurchase:query')")
    @GetMapping(value = "/{id}")
    public R<TPurchase> detail(@PathVariable("id") String id)
    {
        return R.data(tPurchaseService.get(id));
    }

    /**
     * 新增采购产品
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPurchase:add')")
    @Log(title = "采购产品", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TPurchase tPurchase)
    {
        return R.status(tPurchaseService.save(tPurchase));
    }

    /**
     * 修改采购产品
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPurchase:edit')")
    @Log(title = "采购产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TPurchase tPurchase)
    {
        return R.status(tPurchaseService.save(tPurchase));
    }


    /**
     * 删除采购产品
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPurchase:remove')")
    @Log(title = "采购产品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tPurchaseService.deleteTPurchaseByIds(ids));
    }


    /**
     * 导出采购产品列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPurchase:export')")
    @Log(title = "采购产品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TPurchase tPurchase)
    {
        List<TPurchase> list = tPurchaseService.findList(tPurchase);
        ExcelUtil<TPurchase> util = new ExcelUtil<TPurchase>(TPurchase.class);
        return util.exportExcel(list, "采购产品数据");
    }

    /**
     * 查询采购产品列表(二开，用于发货记录）
     */
    @GetMapping("/purchaseList")
    public R<PageInfo> purchaseList(TPurchase tPurchase, HttpServletRequest request, HttpServletResponse response)
    {
        tPurchase.setPage(new PageDomain(request, response));
        return R.data(tPurchaseService.findPurchaseListPage(tPurchase));
    }

}
