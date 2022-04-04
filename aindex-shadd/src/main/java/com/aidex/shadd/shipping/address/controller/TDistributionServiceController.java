package com.aidex.shadd.shipping.address.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.shipping.address.domain.TDistributionService;
import com.aidex.shadd.shipping.address.service.TDistributionServiceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 配送服务子表Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
@RestController
@RequestMapping("/shadd/tDistributionService")
public class TDistributionServiceController extends BaseController
{
    @Autowired
    private TDistributionServiceService tDistributionServiceService;

    /**
     * 查询配送服务子表列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tDistributionService:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TDistributionService tDistributionService, HttpServletRequest request, HttpServletResponse response)
    {
        tDistributionService.setPage(new PageDomain(request, response));
        return R.data(tDistributionServiceService.findPage(tDistributionService));
    }

    /**
     * 获取配送服务子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tDistributionService:query')")
    @GetMapping(value = "/{id}")
    public R<TDistributionService> detail(@PathVariable("id") String id)
    {
        return R.data(tDistributionServiceService.get(id));
    }

    /**
     * 新增配送服务子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tDistributionService:add')")
    @Log(title = "配送服务子表", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TDistributionService tDistributionService)
    {
        return R.status(tDistributionServiceService.save(tDistributionService));
    }

    /**
     * 修改配送服务子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tDistributionService:edit')")
    @Log(title = "配送服务子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TDistributionService tDistributionService)
    {
        return R.status(tDistributionServiceService.save(tDistributionService));
    }


    /**
     * 删除配送服务子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tDistributionService:remove')")
    @Log(title = "配送服务子表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tDistributionServiceService.deleteTDistributionServiceByIds(ids));
    }


    /**
     * 导出配送服务子表列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tDistributionService:export')")
    @Log(title = "配送服务子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TDistributionService tDistributionService)
    {
        List<TDistributionService> list = tDistributionServiceService.findList(tDistributionService);
        ExcelUtil<TDistributionService> util = new ExcelUtil<TDistributionService>(TDistributionService.class);
        return util.exportExcel(list, "配送服务子表数据");
    }

}
