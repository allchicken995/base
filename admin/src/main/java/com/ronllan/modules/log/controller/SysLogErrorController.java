package com.ronllan.modules.log.controller;

import com.github.xiaoymin.knife4j.annotations.Ignore;
import com.ronllan.common.annotation.LogOperation;
import com.ronllan.common.utils.ExcelUtils;
import com.ronllan.modules.log.dto.SysLogErrorDto;
import com.ronllan.modules.log.excel.SysLogErrorExcel;
import com.ronllan.modules.log.service.SysLogErrorService;

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
 * 异常日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@AllArgsConstructor
@RestController
@RequestMapping("sys/log/error")
@Tag(name = "异常日志")
public class SysLogErrorController {
    private final SysLogErrorService sysLogErrorService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)")
    })
    @RequiresPermissions("sys:log:error")
    public Result<PageData<SysLogErrorDto>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysLogErrorDto> page = sysLogErrorService.page(params);

        return new Result<PageData<SysLogErrorDto>>().ok(page);
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    @RequiresPermissions("sys:log:error")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysLogErrorDto> list = sysLogErrorService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "异常日志", list, SysLogErrorExcel.class);
    }

}