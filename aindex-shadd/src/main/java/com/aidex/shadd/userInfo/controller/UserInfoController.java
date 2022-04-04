package com.aidex.shadd.userInfo.controller;

import com.aidex.common.annotation.Log;
import com.aidex.common.core.controller.BaseController;
import com.aidex.common.core.domain.R;
import com.aidex.common.core.page.PageDomain;
import com.aidex.common.enums.BusinessType;
import com.aidex.common.utils.poi.ExcelUtil;
import com.aidex.shadd.userInfo.domain.UserInfo;
import com.aidex.shadd.userInfo.service.UserInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户信息Controller
 * @author 钟良
 * @email 876154169@qq.com
 * @date 2022-04-03
 */
@RestController
@RequestMapping("/shadd/userInfo")
public class UserInfoController extends BaseController
{
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:userInfo:list')")
    @GetMapping("/list")
    public R<PageInfo> list(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response)
    {
        userInfo.setPage(new PageDomain(request, response));
        return R.data(userInfoService.findPage(userInfo));
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:userInfo:query')")
    @GetMapping(value = "/{id}")
    public R<UserInfo> detail(@PathVariable("id") String id)
    {
        return R.data(userInfoService.get(id));
    }

    /**
     * 新增用户信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:userInfo:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody @Validated  UserInfo userInfo)
    {
        return R.status(userInfoService.save(userInfo));
    }

    /**
     * 修改用户信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:userInfo:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody @Validated UserInfo userInfo)
    {
        return R.status(userInfoService.save(userInfo));
    }


    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPermi('shadd:userInfo:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids)
    {
        return R.status(userInfoService.deleteUserInfoByIds(ids));
    }


    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('shadd:userInfo:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R export(UserInfo userInfo)
    {
        List<UserInfo> list = userInfoService.findList(userInfo);
        ExcelUtil<UserInfo> util = new ExcelUtil<UserInfo>(UserInfo.class);
        return util.exportExcel(list, "用户信息数据");
    }

}
