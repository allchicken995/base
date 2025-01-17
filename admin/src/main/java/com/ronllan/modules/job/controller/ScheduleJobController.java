package com.ronllan.modules.job.controller;

import com.github.xiaoymin.knife4j.annotations.Ignore;
import com.ronllan.common.annotation.LogOperation;
import com.ronllan.modules.job.dto.ScheduleJobDto;
import com.ronllan.modules.job.service.ScheduleJobService;

import com.ronllan.common.constant.Constant;
import com.ronllan.common.page.PageData;
import com.ronllan.common.utils.Result;
import com.ronllan.common.validator.ValidatorUtils;
import com.ronllan.common.validator.group.AddGroup;
import com.ronllan.common.validator.group.DefaultGroup;
import com.ronllan.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 *
 * @author glq gugameds066@gmail.com
 */
@RestController
@RequestMapping("/sys/schedule")
@Tag(name = "定时任务")
@AllArgsConstructor
public class ScheduleJobController {
    private final ScheduleJobService scheduleJobServiceImpl;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)"),
            @Parameter(name = "beanName", description = "beanName")
    })
    @RequiresPermissions("sys:schedule:page")
    public Result<PageData<ScheduleJobDto>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<ScheduleJobDto> page = scheduleJobServiceImpl.page(params);
        return new Result<PageData<ScheduleJobDto>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:schedule:info")
    public Result<ScheduleJobDto> info(@PathVariable("id") Long id) {
        ScheduleJobDto schedule = scheduleJobServiceImpl.get(id);
        return new Result<ScheduleJobDto>().ok(schedule);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:schedule:save")
    public Result save(@RequestBody ScheduleJobDto dto) {
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        scheduleJobServiceImpl.save(dto);
        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:schedule:update")
    public Result update(@RequestBody ScheduleJobDto dto) {
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        scheduleJobServiceImpl.update(dto);
        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:schedule:delete")
    public Result delete(@RequestBody Long[] ids) {
    	scheduleJobServiceImpl.deleteBatch(ids);
        return new Result();
    }

    @PutMapping("/run")
    @Operation(summary = "立即执行")
    @LogOperation("立即执行")
    @RequiresPermissions("sys:schedule:run")
    public Result run(@RequestBody Long[] ids) {
    	scheduleJobServiceImpl.run(ids);
        return new Result();
    }

    @PutMapping("/pause")
    @Operation(summary = "暂停")
    @LogOperation("暂停")
    @RequiresPermissions("sys:schedule:pause")
    public Result pause(@RequestBody Long[] ids) {
    	scheduleJobServiceImpl.pause(ids);
        return new Result();
    }

    @PutMapping("/resume")
    @Operation(summary = "恢复")
    @LogOperation("恢复")
    @RequiresPermissions("sys:schedule:resume")
    public Result resume(@RequestBody Long[] ids) {
    	scheduleJobServiceImpl.resume(ids);
        return new Result();
    }

}