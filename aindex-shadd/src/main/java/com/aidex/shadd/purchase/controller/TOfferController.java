package com.aidex.shadd.purchase.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.purchase.domain.TOffer;
import com.aidex.shadd.purchase.service.TOfferService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 厂家报价子表Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-11
 */
@RestController
@RequestMapping("/shadd/tOffer")
public class TOfferController extends BaseController
{
    @Autowired
    private TOfferService tOfferService;

    /**
     * 查询厂家报价子表列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOffer:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TOffer tOffer, HttpServletRequest request, HttpServletResponse response)
    {
        tOffer.setPage(new PageDomain(request, response));
        return R.data(tOfferService.findPage(tOffer));
    }

    /**
     * 获取厂家报价子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOffer:query')")
    @GetMapping(value = "/{id}")
    public R<TOffer> detail(@PathVariable("id") String id)
    {
        return R.data(tOfferService.get(id));
    }

    /**
     * 新增厂家报价子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOffer:add')")
    @Log(title = "厂家报价子表", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TOffer tOffer)
    {
        return R.status(tOfferService.save(tOffer));
    }

    /**
     * 修改厂家报价子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOffer:edit')")
    @Log(title = "厂家报价子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TOffer tOffer)
    {
        return R.status(tOfferService.save(tOffer));
    }


    /**
     * 删除厂家报价子表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOffer:remove')")
    @Log(title = "厂家报价子表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tOfferService.deleteTOfferByIds(ids));
    }


    /**
     * 导出厂家报价子表列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tOffer:export')")
    @Log(title = "厂家报价子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TOffer tOffer)
    {
        List<TOffer> list = tOfferService.findList(tOffer);
        ExcelUtil<TOffer> util = new ExcelUtil<TOffer>(TOffer.class);
        return util.exportExcel(list, "厂家报价子表数据");
    }

}
