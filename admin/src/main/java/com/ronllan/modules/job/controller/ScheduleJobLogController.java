package com.ronllan.modules.job.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ronllan.common.constant.Constant;
import com.ronllan.common.page.PageData;
import com.ronllan.common.utils.Result;
import com.ronllan.modules.job.dto.ScheduleJobLogDto;
import com.ronllan.modules.job.service.ScheduleJobLogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 定时任务日志
 *
 * @author glq gugameds066@gmail.com
 */
@RestController
@RequestMapping("/sys/scheduleLog")
@Tag(name = "定时任务日志")
@AllArgsConstructor
public class ScheduleJobLogController {
    private final ScheduleJobLogService scheduleJobLogService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)"),
            @Parameter(name = "jobId", description = "jobId")
    })
    @RequiresPermissions("sys:schedule:log")
    public Result<PageData<ScheduleJobLogDto>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<ScheduleJobLogDto> page = scheduleJobLogService.page(params);
        return new Result<PageData<ScheduleJobLogDto>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:schedule:log")
    public Result<ScheduleJobLogDto> info(@PathVariable("id") Long id) {
        ScheduleJobLogDto log = scheduleJobLogService.get(id);
        return new Result<ScheduleJobLogDto>().ok(log);
    }
}