package com.ronllan.modules.log.controller;

import com.github.xiaoymin.knife4j.annotations.Ignore;
import com.ronllan.common.annotation.LogOperation;
import com.ronllan.common.utils.ExcelUtils;
import com.ronllan.modules.log.dto.SysLogLoginDto;
import com.ronllan.modules.log.excel.SysLogLoginExcel;
import com.ronllan.modules.log.service.SysLogLoginService;

import com.ronllan.common.constant.Constant;
import com.ronllan.common.page.PageData;
import com.ronllan.common.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 登录日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@AllArgsConstructor
@RestController
@RequestMapping("sys/log/login")
@Tag(name = "登录日志")
public class SysLogLoginController {
    private final SysLogLoginService sysLogLoginService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)"),
            @Parameter(name = "status", description = "状态  0：失败    1：成功    2：账号已锁定"),
            @Parameter(name = "creatorName", description = "用户名")
    })
    @RequiresPermissions("sys:log:login")
    public Result<PageData<SysLogLoginDto>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysLogLoginDto> page = sysLogLoginService.page(params);

        return new Result<PageData<SysLogLoginDto>>().ok(page);
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    @RequiresPermissions("sys:log:login")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysLogLoginDto> list = sysLogLoginService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "登录日志", list, SysLogLoginExcel.class);
    }

}