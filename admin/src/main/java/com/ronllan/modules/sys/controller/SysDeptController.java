package com.ronllan.modules.sys.controller;

import com.ronllan.common.utils.Result;
import com.ronllan.common.validator.AssertUtils;
import com.ronllan.common.validator.ValidatorUtils;
import com.ronllan.common.validator.group.AddGroup;
import com.ronllan.common.validator.group.DefaultGroup;
import com.ronllan.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import com.ronllan.common.annotation.LogOperation;
import com.ronllan.modules.sys.dto.SysDeptDto;
import com.ronllan.modules.sys.service.SysDeptService;
import com.ronllan.modules.sys.service.SysUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("/sys/dept")
@Tag(name = "部门管理")
public class SysDeptController {
    private final SysDeptService sysDeptServiceImpl;
    private final SysUserService sysUserServiceImpl;

    @GetMapping("list")
    @Operation(summary = "列表")
    @RequiresPermissions("sys:dept:list")
    public Result<List<SysDeptDto>> list(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        List<SysDeptDto> list = sysDeptServiceImpl.list(params);
        return new Result<List<SysDeptDto>>().ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:dept:info")
    public Result<SysDeptDto> get(@PathVariable("id") Long id) {
        SysDeptDto data = sysDeptServiceImpl.get(id);
        if (data.getLeaderId() != null) {
            data.setLeaderName(sysUserServiceImpl.get(data.getLeaderId()).getRealName());
        }
        return new Result<SysDeptDto>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:dept:save")
    public Result save(@RequestBody SysDeptDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        sysDeptServiceImpl.save(dto);
        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:dept:update")
    public Result update(@RequestBody SysDeptDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        sysDeptServiceImpl.update(dto);
        return new Result();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:dept:delete")
    public Result delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtils.isNull(id, "id");
        sysDeptServiceImpl.delete(id);
        return new Result();
    }

}