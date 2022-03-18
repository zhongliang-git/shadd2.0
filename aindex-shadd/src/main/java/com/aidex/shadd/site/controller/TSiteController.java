package com.aidex.shadd.site.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.site.domain.TSite;
import com.aidex.shadd.site.service.TSiteService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 站点Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
@RestController
@RequestMapping("/shadd/tSite")
public class TSiteController extends BaseController
{
    @Autowired
    private TSiteService tSiteService;

    /**
     * 查询站点列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSite:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TSite tSite, HttpServletRequest request, HttpServletResponse response)
    {
        tSite.setPage(new PageDomain(request, response));
        return R.data(tSiteService.findPage(tSite));
    }

    /**
     * 获取站点详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSite:query')")
    @GetMapping(value = "/{id}")
    public R<TSite> detail(@PathVariable("id") String id)
    {
        return R.data(tSiteService.get(id));
    }

    /**
     * 新增站点
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSite:add')")
    @Log(title = "站点", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TSite tSite)
    {
        return R.status(tSiteService.save(tSite));
    }

    /**
     * 修改站点
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSite:edit')")
    @Log(title = "站点", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TSite tSite)
    {
        return R.status(tSiteService.save(tSite));
    }


    /**
     * 删除站点
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSite:remove')")
    @Log(title = "站点", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tSiteService.deleteTSiteByIds(ids));
    }


    /**
     * 校验站点编码的唯一性
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSite:query')")
    @Log(title = "站点", businessType = BusinessType.CHECK)
    @GetMapping(value = {"/checkCodeUnique/{code}/{id}", "/checkCodeUnique/{code}"})
    public R checkCodeUnique(@NotBlank(message = "站点编码不允许为空") @PathVariable("code") String code, @PathVariable(value = "id", required = false) String id) {
        TSite tSite = new TSite();
        tSite.setCode(code);
        tSite.setId(id);
        tSiteService.checkCodeUnique(tSite);
        return R.status(Boolean.TRUE);
    }

    /**
     * 导出站点列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tSite:export')")
    @Log(title = "站点", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TSite tSite)
    {
        List<TSite> list = tSiteService.findList(tSite);
        ExcelUtil<TSite> util = new ExcelUtil<TSite>(TSite.class);
        return util.exportExcel(list, "站点数据");
    }

}
