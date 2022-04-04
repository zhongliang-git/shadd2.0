package com.aidex.shadd.payrecord.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.payrecord.domain.TPayRecord;
import com.aidex.shadd.payrecord.service.TPayRecordService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 充值记录Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
@RestController
@RequestMapping("/shadd/tPayRecord")
public class TPayRecordController extends BaseController
{
    @Autowired
    private TPayRecordService tPayRecordService;

    /**
     * 查询充值记录列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPayRecord:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TPayRecord tPayRecord, HttpServletRequest request, HttpServletResponse response)
    {
        tPayRecord.setPage(new PageDomain(request, response));
        return R.data(tPayRecordService.findPage(tPayRecord));
    }

    /**
     * 获取充值记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPayRecord:query')")
    @GetMapping(value = "/{id}")
    public R<TPayRecord> detail(@PathVariable("id") String id)
    {
        return R.data(tPayRecordService.get(id));
    }

    /**
     * 新增充值记录
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPayRecord:add')")
    @Log(title = "充值记录", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TPayRecord tPayRecord)
    {
        return R.status(tPayRecordService.saveAndUpdateBalance(tPayRecord));
    }

    /**
     * 修改充值记录
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPayRecord:edit')")
    @Log(title = "充值记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TPayRecord tPayRecord)
    {
        return R.status(tPayRecordService.save(tPayRecord));
    }


    /**
     * 删除充值记录
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPayRecord:remove')")
    @Log(title = "充值记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tPayRecordService.deleteTPayRecordByIds(ids));
    }


    /**
     * 导出充值记录列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tPayRecord:export')")
    @Log(title = "充值记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TPayRecord tPayRecord)
    {
        List<TPayRecord> list = tPayRecordService.findList(tPayRecord);
        ExcelUtil<TPayRecord> util = new ExcelUtil<TPayRecord>(TPayRecord.class);
        return util.exportExcel(list, "充值记录数据");
    }

}
