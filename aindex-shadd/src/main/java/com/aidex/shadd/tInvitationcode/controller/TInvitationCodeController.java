package com.aidex.shadd.tInvitationcode.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.tInvitationcode.domain.TInvitationCode;
import com.aidex.shadd.tInvitationcode.service.TInvitationCodeService;
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
 * 邀请码Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
@RestController
@RequestMapping("/shadd/tInvitationCode")
public class TInvitationCodeController extends BaseController
{
    @Autowired
    private TInvitationCodeService tInvitationCodeService;

    /**
     * 查询邀请码列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tInvitationCode:list')")
    @GetMapping("/list")
    public R<PageInfo> list(TInvitationCode tInvitationCode, HttpServletRequest request, HttpServletResponse response)
    {
        tInvitationCode.setPage(new PageDomain(request, response));
        return R.data(tInvitationCodeService.findPage(tInvitationCode));
    }

    /**
     * 获取邀请码详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:tInvitationCode:query')")
    @GetMapping(value = "/{id}")
    public R<TInvitationCode> detail(@PathVariable("id") String id)
    {
        return R.data(tInvitationCodeService.get(id));
    }

    /**
     * 新增邀请码
     */
    @PreAuthorize("@ss.hasPermi('shadd:tInvitationCode:add')")
    @Log(title = "邀请码", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  TInvitationCode tInvitationCode)
    {
        return R.status(tInvitationCodeService.save(tInvitationCode));
    }

    /**
     * 修改邀请码
     */
    @PreAuthorize("@ss.hasPermi('shadd:tInvitationCode:edit')")
    @Log(title = "邀请码", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated TInvitationCode tInvitationCode)
    {
        return R.status(tInvitationCodeService.save(tInvitationCode));
    }


    /**
     * 删除邀请码
     */
    @PreAuthorize("@ss.hasPermi('shadd:tInvitationCode:remove')")
    @Log(title = "邀请码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(tInvitationCodeService.deleteTInvitationCodeByIds(ids));
    }


    /**
     * 校验邀请码的唯一性
     */
    @PreAuthorize("@ss.hasPermi('shadd:tInvitationCode:query')")
    @Log(title = "邀请码", businessType = BusinessType.CHECK)
    @GetMapping(value = {"/checkCodeUnique/{code}/{id}", "/checkCodeUnique/{code}"})
    public R checkCodeUnique(@NotBlank(message = "邀请码不允许为空") @PathVariable("code") String code, @PathVariable(value = "id", required = false) String id) {
        TInvitationCode tInvitationCode = new TInvitationCode();
        tInvitationCode.setCode(code);
        tInvitationCode.setId(id);
        tInvitationCodeService.checkCodeUnique(tInvitationCode);
        return R.status(Boolean.TRUE);
    }

    /**
     * 导出邀请码列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:tInvitationCode:export')")
    @Log(title = "邀请码", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(TInvitationCode tInvitationCode)
    {
        List<TInvitationCode> list = tInvitationCodeService.findList(tInvitationCode);
        ExcelUtil<TInvitationCode> util = new ExcelUtil<TInvitationCode>(TInvitationCode.class);
        return util.exportExcel(list, "邀请码数据");
    }

    @PreAuthorize("@ss.hasPermi('shadd:tInvitationCode:add')")
    @Log(title = "生成邀请码", businessType = BusinessType.EXPORT)
    @PostMapping("/create")
    public R create() {
        return R.data(tInvitationCodeService.create());
    }
}
