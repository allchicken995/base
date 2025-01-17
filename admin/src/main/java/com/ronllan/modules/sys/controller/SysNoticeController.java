package com.ronllan.modules.sys.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ronllan.common.annotation.LogOperation;
import com.ronllan.common.constant.Constant;
import com.ronllan.common.page.PageData;
import com.ronllan.common.utils.Result;
import com.ronllan.common.validator.AssertUtils;
import com.ronllan.common.validator.ValidatorUtils;
import com.ronllan.common.validator.group.AddGroup;
import com.ronllan.common.validator.group.DefaultGroup;
import com.ronllan.common.validator.group.UpdateGroup;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dto.SysNoticeDto;
import com.ronllan.modules.sys.dto.SysNoticeUserDto;
import com.ronllan.modules.sys.service.SysNoticeService;
import com.ronllan.modules.sys.service.SysNoticeUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;


/**
 * 通知管理
 *
 * @author @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("sys/notice")
@Tag(name = "通知管理")
public class SysNoticeController {
    private final SysNoticeService sysNoticeServiceImpl;
    private final SysNoticeUserService sysNoticeUserServiceImpl;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)")
    })
    @RequiresPermissions("sys:notice:page")
    public Result<PageData<SysNoticeDto>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysNoticeDto> page = sysNoticeServiceImpl.page(params);
        return new Result<PageData<SysNoticeDto>>().ok(page);
    }

    @GetMapping("myNotice/page")
    @Operation(summary = "获取我的通知")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)")
    })
    public Result<PageData<SysNoticeDto>> myNoticePage(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysNoticeDto> page = sysNoticeServiceImpl.getMyNoticePage(params);
        return new Result<PageData<SysNoticeDto>>().ok(page);
    }

    @PutMapping("myNotice/read/{noticeId}")
    @Operation(summary = "标记我的通知为已读")
    public Result read(@PathVariable("noticeId") Long noticeId) {
    	SysNoticeUserDto dto = new SysNoticeUserDto();
    	dto.setNoticeId(noticeId);
    	dto.setReceiverId(SecurityUser.getUserId());
    	sysNoticeUserServiceImpl.update(dto);
        return new Result();
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:notice:all")
    public Result<SysNoticeDto> get(@PathVariable("id") Long id) {
        return new Result<SysNoticeDto>().ok(sysNoticeServiceImpl.get(id));
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:notice:save")
    public Result save(@RequestBody SysNoticeDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        sysNoticeServiceImpl.save(dto);
        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:notice:update")
    public Result update(@RequestBody SysNoticeDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        sysNoticeServiceImpl.update(dto);
        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:notice:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        sysNoticeServiceImpl.delete(ids);
        return new Result();
    }

}