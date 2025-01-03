package com.ronllan.modules.sys.controller;

import java.util.HashMap;
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
import com.ronllan.common.validator.group.AddGroup;
import com.ronllan.common.validator.group.DefaultGroup;
import com.ronllan.common.validator.group.UpdateGroup;
import com.ronllan.modules.sys.dto.SysRoleDto;
import com.ronllan.modules.sys.service.SysRoleDataScopeService;
import com.ronllan.modules.sys.service.SysRoleMenuService;
import com.ronllan.modules.sys.service.SysRoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 角色管理
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("/sys/role")
@Tag(name = "角色管理")
public class SysRoleController {
    private final SysRoleService sysRoleService;
    private final SysRoleMenuService sysRoleMenuService;
    private final SysRoleDataScopeService sysRoleDataScopeService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)"),
            @Parameter(name = "name", description = "角色名")
    })
    @RequiresPermissions("sys:role:page")
    public Result<PageData<SysRoleDto>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysRoleDto> page = sysRoleService.page(params);
        return new Result<PageData<SysRoleDto>>().ok(page);
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    @RequiresPermissions("sys:role:list")
    public Result<List<SysRoleDto>> list() {
        List<SysRoleDto> data = sysRoleService.list(new HashMap<>(1));
        return new Result<List<SysRoleDto>>().ok(data);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:role:info")
    public Result<SysRoleDto> get(@PathVariable("id") Long id) {
        SysRoleDto data = sysRoleService.get(id);
        //查询角色对应的菜单
        data.setMenuRoleList(sysRoleMenuService.getMenuRoleList(id));
        //查询角色对应的数据权限
        List<Long> deptIdList = sysRoleDataScopeService.getDeptIdList(id);
        data.setDeptIdList(deptIdList);
        return new Result<SysRoleDto>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:role:save")
    public Result save(@RequestBody SysRoleDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        sysRoleService.save(dto);
        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:role:update")
    public Result update(@RequestBody SysRoleDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        sysRoleService.update(dto);
        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:role:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        sysRoleService.delete(ids);
        return new Result();
    }
}