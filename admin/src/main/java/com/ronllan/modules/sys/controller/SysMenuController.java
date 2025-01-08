package com.ronllan.modules.sys.controller;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ronllan.common.annotation.LogOperation;
import com.ronllan.common.user.UserDetail;
import com.ronllan.common.utils.Result;
import com.ronllan.common.validator.AssertUtils;
import com.ronllan.common.validator.ValidatorUtils;
import com.ronllan.common.validator.group.DefaultGroup;
import com.ronllan.modules.security.service.ShiroService;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dto.SysMenuDto;
import com.ronllan.modules.sys.enums.MenuTypeEnum;
import com.ronllan.modules.sys.service.SysMenuService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

/**
 * 菜单管理
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("/sys/menu")
@Tag(name = "菜单管理")
public class SysMenuController {
    private final SysMenuService sysMenuService;
    private final ShiroService shiroService;

    @GetMapping("nav")
    @Operation(summary = "导航")
    public Result<List<SysMenuDto>> nav() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuDto> list = sysMenuService.getUserMenuList(user, MenuTypeEnum.MENU.value());
        return new Result<List<SysMenuDto>>().ok(list);
    }

    @GetMapping("permissions")
    @Operation(summary = "权限标识")
    public Result<Set<String>> permissions() {
        UserDetail user = SecurityUser.getUser();
        Set<String> set = shiroService.getUserPermissions(user);
        return new Result<Set<String>>().ok(set);
    }

    @GetMapping("list")
    @Operation(summary = "列表")
    @Parameter(name = "type", description = "菜单类型 0：菜单 1：按钮  null：全部")
    public Result<List<SysMenuDto>> list(Integer type) {
        List<SysMenuDto> list = sysMenuService.getAllMenuList(type);
        return new Result<List<SysMenuDto>>().ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:menu:info")
    public Result<SysMenuDto> get(@PathVariable("id") Long id) {
        SysMenuDto data = sysMenuService.get(id);
        return new Result<SysMenuDto>().ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:menu:save")
    public Result save(@RequestBody SysMenuDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);
        sysMenuService.save(dto);
        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:menu:update")
    public Result update(@RequestBody SysMenuDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);
        sysMenuService.update(dto);
        return new Result();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:menu:delete")
    public Result delete(@PathVariable("id") Long id) {
        //效验数据
        AssertUtils.isNull(id, "id");
        sysMenuService.delete(id);
        return new Result();
    }

    @GetMapping("select")
    @Operation(summary = "角色菜单权限")
    @RequiresPermissions("sys:menu:select")
    public Result<List<SysMenuDto>> select() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuDto> list = sysMenuService.getUserMenuList(user, null);
        return new Result<List<SysMenuDto>>().ok(list);
    }
}