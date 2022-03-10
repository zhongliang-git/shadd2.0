package com.aidex.shadd.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.domain.TFactory;
import com.aidex.shadd.service.TFactoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 厂家Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-08
 */
@RestController
@RequestMapping("/shadd/tFactory")
public class TFactoryController extends BaseController
{
    @Autowired
    private TFactoryService tFactoryService;

    /**
     * 查询厂家列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tFactory:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TFactory tFactory, HttpServletRequest request, HttpServletResponse response)
    {
        tFactory.setPage(new PageDomain(request, response));
        return R.data(tFactoryService.findPage(tFactory));
    }

    /**
     * 获取厂家详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tFactory:query')")
    @GetMapping(value = "/{id}")
    public R<TFactory> detail(@PathVariable("id") String id)
    {
        TFactory tFactory = tFactoryService.get(id);
        if (CollectionUtils.isEmpty(tFactory.getPurchaseList())) {
            tFactory.setPurchaseList(new ArrayList<>(16));
        }
        return R.data(tFactory);
    }

    /**
     * 新增厂家
     */
    @PreAuthorize("@ss.hasPermi('shadd:tFactory:add')")
    @Log(title = "厂家", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TFactory tFactory)
    {
        tFactoryService.save(tFactory);
        return R.data(tFactory);
    }

    /**
     * 修改厂家
     */
    @PreAuthorize("@ss.hasPermi('shadd:tFactory:edit')")
    @Log(title = "厂家", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TFactory tFactory)
    {
        tFactoryService.save(tFactory);
        return R.data(tFactory);
    }

    /**
     * 更新厂家状态
     */
    @PreAuthorize("@ss.hasPermi('shadd:tFactory:edit')")
    @Log(title = "厂家", businessType = BusinessType.UPDATE)
    @PutMapping("/updateStatus")
    public R updateStatus(@RequestBody TFactory tFactory)
    {
        return R.status(tFactoryService.updateStatus(tFactory));
    }

    /**
     * 删除厂家
     */
    @PreAuthorize("@ss.hasPermi('shadd:tFactory:remove')")
    @Log(title = "厂家", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tFactoryService.deleteTFactoryByIds(ids));
    }


    /**
     * 导出厂家列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tFactory:export')")
    @Log(title = "厂家", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TFactory tFactory)
    {
        List<TFactory> list = tFactoryService.findList(tFactory);
        ExcelUtil<TFactory> util = new ExcelUtil<TFactory>(TFactory.class);
        return util.exportExcel(list, "厂家数据");
    }

}
