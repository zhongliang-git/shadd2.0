package com.aidex.shadd.repertory.controller;

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
import com.aidex.shadd.repertory.domain.TRepertory;
import com.aidex.shadd.repertory.service.TRepertoryService;

/**
 * 库存Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-19
 */
@RestController
@RequestMapping("/shadd/tRepertory")
public class TRepertoryController extends BaseController
{
    @Autowired
    private TRepertoryService tRepertoryService;

    /**
     * 查询库存列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tRepertory:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TRepertory tRepertory, HttpServletRequest request, HttpServletResponse response)
    {
        tRepertory.setPage(new PageDomain(request, response));
        return R.data(tRepertoryService.findPage(tRepertory));
    }

    /**
     * 获取库存详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tRepertory:query')")
    @GetMapping(value = "/{id}")
    public R<TRepertory> detail(@PathVariable("id") String id)
    {
        return R.data(tRepertoryService.get(id));
    }

    /**
     * 新增库存
     */
    @PreAuthorize("@ss.hasPermi('shadd:tRepertory:add')")
    @Log(title = "库存", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TRepertory tRepertory)
    {
        return R.status(tRepertoryService.save(tRepertory));
    }

    /**
     * 修改库存
     */
    @PreAuthorize("@ss.hasPermi('shadd:tRepertory:edit')")
    @Log(title = "库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TRepertory tRepertory)
    {
        return R.status(tRepertoryService.save(tRepertory));
    }


    /**
     * 删除库存
     */
    @PreAuthorize("@ss.hasPermi('shadd:tRepertory:remove')")
    @Log(title = "库存", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tRepertoryService.deleteTRepertoryByIds(ids));
    }


    /**
     * 导出库存列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tRepertory:export')")
    @Log(title = "库存", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TRepertory tRepertory)
    {
        List<TRepertory> list = tRepertoryService.findList(tRepertory);
        ExcelUtil<TRepertory> util = new ExcelUtil<TRepertory>(TRepertory.class);
        return util.exportExcel(list, "库存数据");
    }

}
