package com.aidex.shadd.releaserecords.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.releaserecords.domain.TGoodsTransport;
import com.aidex.shadd.releaserecords.service.TGoodsTransportService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 货物运输子表Controller
 * @author ${functionAuthor}
 * @email ${functionAuthorEmail}
 * @date 2022-03-16
 */
@RestController
@RequestMapping("/shadd/tGoodsTransport")
public class TGoodsTransportController extends BaseController
{
    @Autowired
    private TGoodsTransportService tGoodsTransportService;

    /**
     * 查询货物运输子表列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tGoodsTransport:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TGoodsTransport tGoodsTransport, HttpServletRequest request, HttpServletResponse response)
    {
        tGoodsTransport.setPage(new PageDomain(request, response));
        return R.data(tGoodsTransportService.findPage(tGoodsTransport));
    }

    /**
     * 获取货物运输子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tGoodsTransport:query')")
    @GetMapping(value = "/{id}")
    public R<TGoodsTransport> detail(@PathVariable("id") String id)
    {
        return R.data(tGoodsTransportService.get(id));
    }

    /**
     * 新增货物运输子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tGoodsTransport:add')")
    @Log(title = "货物运输子表", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TGoodsTransport tGoodsTransport)
    {
        return R.status(tGoodsTransportService.save(tGoodsTransport));
    }

    /**
     * 修改货物运输子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tGoodsTransport:edit')")
    @Log(title = "货物运输子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TGoodsTransport tGoodsTransport)
    {
        return R.status(tGoodsTransportService.save(tGoodsTransport));
    }


    /**
     * 删除货物运输子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tGoodsTransport:remove')")
    @Log(title = "货物运输子表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tGoodsTransportService.deleteTGoodsTransportByIds(ids));
    }


    /**
     * 导出货物运输子表列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tGoodsTransport:export')")
    @Log(title = "货物运输子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TGoodsTransport tGoodsTransport)
    {
        List<TGoodsTransport> list = tGoodsTransportService.findList(tGoodsTransport);
        ExcelUtil<TGoodsTransport> util = new ExcelUtil<TGoodsTransport>(TGoodsTransport.class);
        return util.exportExcel(list, "货物运输子表数据");
    }

}
