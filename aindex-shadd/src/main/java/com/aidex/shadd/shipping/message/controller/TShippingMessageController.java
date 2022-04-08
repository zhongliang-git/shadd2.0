package com.aidex.shadd.shipping.message.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.shipping.message.domain.TShippingMessage;
import com.aidex.shadd.shipping.message.service.TShippingMessageService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 收货信息Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-03-27
 */
@RestController
@RequestMapping("/shadd/tShippingMessage")
public class TShippingMessageController extends BaseController
{
    @Autowired
    private TShippingMessageService tShippingMessageService;

    /**
     * 查询收货信息列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingMessage:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TShippingMessage tShippingMessage, HttpServletRequest request, HttpServletResponse response)
    {
        tShippingMessage.setPage(new PageDomain(request, response));
        return R.data(tShippingMessageService.findPage(tShippingMessage));
    }

    /**
     * 获取收货信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingMessage:query')")
    @GetMapping(value = "/{id}")
    public R<TShippingMessage> detail(@PathVariable("id") String id)
    {
        return R.data(tShippingMessageService.get(id));
    }

    /**
     * 新增收货信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingMessage:add')")
    @Log(title = "收货信息", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TShippingMessage tShippingMessage)
    {
        return R.status(tShippingMessageService.save(tShippingMessage));
    }

    /**
     * 修改收货信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingMessage:edit')")
    @Log(title = "收货信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TShippingMessage tShippingMessage)
    {
        return R.status(tShippingMessageService.save(tShippingMessage));
    }


    /**
     * 删除收货信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingMessage:remove')")
    @Log(title = "收货信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tShippingMessageService.deleteTShippingMessageByIds(ids));
    }


    /**
     * 导出收货信息列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tShippingMessage:export')")
    @Log(title = "收货信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TShippingMessage tShippingMessage)
    {
        List<TShippingMessage> list = tShippingMessageService.findList(tShippingMessage);
        ExcelUtil<TShippingMessage> util = new ExcelUtil<TShippingMessage>(TShippingMessage.class);
        return util.exportExcel(list, "收货信息数据");
    }

}
