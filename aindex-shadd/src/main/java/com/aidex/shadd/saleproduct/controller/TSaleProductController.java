package com.aidex.shadd.saleproduct.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.saleproduct.domain.TSaleProduct;
import com.aidex.shadd.saleproduct.service.TSaleProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 销售产品Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
@RestController
@RequestMapping("/shadd/tSaleProduct")
public class TSaleProductController extends BaseController
{
    @Autowired
    private TSaleProductService tSaleProductService;

    /**
     * 查询销售产品列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSaleProduct:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TSaleProduct tSaleProduct, HttpServletRequest request, HttpServletResponse response)
    {
        tSaleProduct.setPage(new PageDomain(request, response));
        return R.data(tSaleProductService.findPage(tSaleProduct));
    }

    /**
     * 获取销售产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSaleProduct:query')")
    @GetMapping(value = "/{id}")
    public R<TSaleProduct> detail(@PathVariable("id") String id)
    {
        return R.data(tSaleProductService.get(id));
    }

    /**
     * 新增销售产品
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSaleProduct:add')")
    @Log(title = "销售产品", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TSaleProduct tSaleProduct)
    {
        return R.status(tSaleProductService.save(tSaleProduct));
    }

    /**
     * 修改销售产品
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSaleProduct:edit')")
    @Log(title = "销售产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TSaleProduct tSaleProduct)
    {
        return R.status(tSaleProductService.save(tSaleProduct));
    }


    /**
     * 删除销售产品
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSaleProduct:remove')")
    @Log(title = "销售产品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tSaleProductService.deleteTSaleProductByIds(ids));
    }


    /**
     * 导出销售产品列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSaleProduct:export')")
    @Log(title = "销售产品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TSaleProduct tSaleProduct)
    {
        List<TSaleProduct> list = tSaleProductService.findList(tSaleProduct);
        ExcelUtil<TSaleProduct> util = new ExcelUtil<TSaleProduct>(TSaleProduct.class);
        return util.exportExcel(list, "销售产品数据");
    }

}
