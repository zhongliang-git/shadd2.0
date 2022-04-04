package com.aidex.shadd.shipping.address.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.shipping.address.domain.TShippingAddress;
import com.aidex.shadd.shipping.address.service.TShippingAddressService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 收货地址Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
@RestController
@RequestMapping("/shadd/tShippingAddress")
public class TShippingAddressController extends BaseController
{
    @Autowired
    private TShippingAddressService tShippingAddressService;

    /**
     * 查询收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingAddress:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TShippingAddress tShippingAddress, HttpServletRequest request, HttpServletResponse response)
    {
        tShippingAddress.setPage(new PageDomain(request, response));
        return R.data(tShippingAddressService.findPage(tShippingAddress));
    }

    /**
     * 获取收货地址详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingAddress:query')")
    @GetMapping(value = "/{id}")
    public R<TShippingAddress> detail(@PathVariable("id") String id)
    {
        return R.data(tShippingAddressService.get(id));
    }

    /**
     * 新增收货地址
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingAddress:add')")
    @Log(title = "收货地址", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TShippingAddress tShippingAddress)
    {
        return R.status(tShippingAddressService.save(tShippingAddress));
    }

    /**
     * 修改收货地址
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingAddress:edit')")
    @Log(title = "收货地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TShippingAddress tShippingAddress)
    {
        return R.status(tShippingAddressService.save(tShippingAddress));
    }


    /**
     * 删除收货地址
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingAddress:remove')")
    @Log(title = "收货地址", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tShippingAddressService.deleteTShippingAddressByIds(ids));
    }


    /**
     * 导出收货地址列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingAddress:export')")
    @Log(title = "收货地址", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TShippingAddress tShippingAddress)
    {
        List<TShippingAddress> list = tShippingAddressService.findList(tShippingAddress);
        ExcelUtil<TShippingAddress> util = new ExcelUtil<TShippingAddress>(TShippingAddress.class);
        return util.exportExcel(list, "收货地址数据");
    }

}
