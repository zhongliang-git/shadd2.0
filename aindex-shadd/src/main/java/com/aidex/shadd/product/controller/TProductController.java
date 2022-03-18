package com.aidex.shadd.product.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.product.domain.TProduct;
import com.aidex.shadd.product.service.TProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * 产品Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-10
 */
@RestController
@RequestMapping("/shadd/tProduct")
public class TProductController extends BaseController
{
    @Autowired
    private TProductService tProductService;

    /**
     * 查询产品列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tProduct:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TProduct tProduct, HttpServletRequest request, HttpServletResponse response)
    {
        tProduct.setPage(new PageDomain(request, response));
        return R.data(tProductService.findPage(tProduct));
    }

    @PreAuthorize("@ss.hasPermi('shadd:tProduct:list')")
    @GetMapping("/selectList")
    public R<PageInfo> selectList(TProduct tProduct, HttpServletRequest request, HttpServletResponse response)
    {
        tProduct.setPage(new PageDomain(request, response));
        return R.data(tProductService.findPage(tProduct));
    }

    /**
     * 获取产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tProduct:query')")
    @GetMapping(value = "/{id}")
    public R<TProduct> detail(@PathVariable("id") String id)
    {
        return R.data(tProductService.get(id));
    }

    /**
     * 新增产品
     */
    @PreAuthorize("@ss.hasPermi('shadd:tProduct:add')")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TProduct tProduct)
    {
        return R.status(tProductService.save(tProduct));
    }

    /**
     * 修改产品
     */
    @PreAuthorize("@ss.hasPermi('shadd:tProduct:edit')")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TProduct tProduct)
    {
        return R.status(tProductService.save(tProduct));
    }


    /**
     * 删除产品
     */
    @PreAuthorize("@ss.hasPermi('shadd:tProduct:remove')")
    @Log(title = "产品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tProductService.deleteTProductByIds(ids));
    }


    /**
     * 导出产品列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tProduct:export')")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TProduct tProduct)
    {
        List<TProduct> list = tProductService.findList(tProduct);
        ExcelUtil<TProduct> util = new ExcelUtil<TProduct>(TProduct.class);
        return util.exportExcel(list, "产品数据");
    }

}
