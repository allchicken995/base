package com.ronllan.modules.sys.controller;

import java.util.List;
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
import com.ronllan.common.validator.group.DefaultGroup;
import com.ronllan.common.validator.group.UpdateGroup;
import com.ronllan.modules.sys.dto.SysDictDataDto;
import com.ronllan.modules.sys.service.SysDictDataService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 字典数据
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("sys/dict/data")
@Tag(name = "字典数据")
public class SysDictDataController {
    private final SysDictDataService sysDictDataService;

    @GetMapping("page")
    @Operation(summary = "字典数据")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)"),
            @Parameter(name = "dictLabel", description = "字典标签"),
            @Parameter(name = "dictValue", description = "字典值")
    })
    @RequiresPermissions("sys:dict:page")
    public Result<PageData<SysDictDataDto>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        //字典类型
        PageData<SysDictDataDto> page = sysDictDataService.page(params);
        return new Result<PageData<SysDictDataDto>>().ok(page);
    }
    
    @GetMapping("list")
    @Operation(summary = "列表")
    @RequiresPermissions("sys:dict:list")
    public Result<List<SysDictDataDto>> list(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        List<SysDictDataDto> data = sysDictDataService.list(params);
        return new Result<List<SysDictDataDto>>().ok(data);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:dict:info")
    public Result<SysDictDataDto> get(@PathVariable("id") Long id) {
        SysDictDataDto data = sysDictDataService.get(id);
        return new Result<SysDictDataDto>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:dict:save")
    public Result save(@RequestBody SysDictDataDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);
        sysDictDataService.save(dto);
        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:dict:update")
    public Result update(@RequestBody SysDictDataDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        sysDictDataService.update(dto);
        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:dict:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        sysDictDataService.delete(ids);
        return new Result();
    }

}