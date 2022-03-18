package com.aidex.shadd.shippingline.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.shippingline.domain.TShippingLine;
import com.aidex.shadd.shippingline.service.TShippingLineService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 运线Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-16
 */
@RestController
@RequestMapping("/shadd/tShippingLine")
public class TShippingLineController extends BaseController
{
    @Autowired
    private TShippingLineService tShippingLineService;

    /**
     * 查询运线列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingLine:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TShippingLine tShippingLine, HttpServletRequest request, HttpServletResponse response)
    {
        tShippingLine.setPage(new PageDomain(request, response));
        return R.data(tShippingLineService.findPage(tShippingLine));
    }

    /**
     * 获取运线详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingLine:query')")
    @GetMapping(value = "/{id}")
    public R<TShippingLine> detail(@PathVariable("id") String id)
    {
        return R.data(tShippingLineService.get(id));
    }

    /**
     * 新增运线
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingLine:add')")
    @Log(title = "运线", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TShippingLine tShippingLine)
    {
        return R.status(tShippingLineService.save(tShippingLine));
    }

    /**
     * 修改运线
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingLine:edit')")
    @Log(title = "运线", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TShippingLine tShippingLine)
    {
        return R.status(tShippingLineService.save(tShippingLine));
    }


    /**
     * 删除运线
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingLine:remove')")
    @Log(title = "运线", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tShippingLineService.deleteTShippingLineByIds(ids));
    }


    /**
     * 导出运线列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingLine:export')")
    @Log(title = "运线", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TShippingLine tShippingLine)
    {
        List<TShippingLine> list = tShippingLineService.findList(tShippingLine);
        ExcelUtil<TShippingLine> util = new ExcelUtil<TShippingLine>(TShippingLine.class);
        return util.exportExcel(list, "运线数据");
    }

}
