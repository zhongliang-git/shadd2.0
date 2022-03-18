package com.aidex.shadd.releaserecords.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.releaserecords.domain.TReleaseRecords;
import com.aidex.shadd.releaserecords.service.TReleaseRecordsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 发货记录Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-16
 */
@RestController
@RequestMapping("/shadd/tReleaseRecords")
public class TReleaseRecordsController extends BaseController
{
    @Autowired
    private TReleaseRecordsService tReleaseRecordsService;

    /**
     * 查询发货记录列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tReleaseRecords:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TReleaseRecords tReleaseRecords, HttpServletRequest request, HttpServletResponse response)
    {
        tReleaseRecords.setPage(new PageDomain(request, response));
        return R.data(tReleaseRecordsService.findPage(tReleaseRecords));
    }

    /**
     * 获取发货记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tReleaseRecords:query')")
    @GetMapping(value = "/{id}")
    public R<TReleaseRecords> detail(@PathVariable("id") String id)
    {
        return R.data(tReleaseRecordsService.get(id));
    }

    /**
     * 新增发货记录
     */
    @PreAuthorize("@ss.hasPermi('shadd:tReleaseRecords:add')")
    @Log(title = "发货记录", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TReleaseRecords tReleaseRecords)
    {
        return R.status(tReleaseRecordsService.save(tReleaseRecords));
    }

    /**
     * 修改发货记录
     */
    @PreAuthorize("@ss.hasPermi('shadd:tReleaseRecords:edit')")
    @Log(title = "发货记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TReleaseRecords tReleaseRecords)
    {
        return R.status(tReleaseRecordsService.save(tReleaseRecords));
    }


    /**
     * 删除发货记录
     */
    @PreAuthorize("@ss.hasPermi('shadd:tReleaseRecords:remove')")
    @Log(title = "发货记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tReleaseRecordsService.deleteTReleaseRecordsByIds(ids));
    }


    /**
     * 导出发货记录列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tReleaseRecords:export')")
    @Log(title = "发货记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TReleaseRecords tReleaseRecords)
    {
        List<TReleaseRecords> list = tReleaseRecordsService.findList(tReleaseRecords);
        ExcelUtil<TReleaseRecords> util = new ExcelUtil<TReleaseRecords>(TReleaseRecords.class);
        return util.exportExcel(list, "发货记录数据");
    }

}
