package com.ronllan.modules.sys.controller;

import java.util.Arrays;
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
import com.ronllan.common.exception.DefineException;
import com.ronllan.common.exception.ErrorCode;
import com.ronllan.common.page.PageData;
import com.ronllan.common.user.UserDetail;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.common.utils.ExcelUtils;
import com.ronllan.common.utils.Result;
import com.ronllan.common.validator.AssertUtils;
import com.ronllan.common.validator.ValidatorUtils;
import com.ronllan.common.validator.group.AddGroup;
import com.ronllan.common.validator.group.DefaultGroup;
import com.ronllan.common.validator.group.UpdateGroup;
import com.ronllan.modules.security.password.PasswordUtils;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dto.PasswordDto;
import com.ronllan.modules.sys.dto.SysUserDto;
import com.ronllan.modules.sys.excel.SysUserExcel;
import com.ronllan.modules.sys.service.SysRoleUserService;
import com.ronllan.modules.sys.service.SysUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

/**
 * 用户管理
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@RestController
@RequestMapping("/sys/user")
@Tag(name = "用户管理")
public class SysUserController {
    private final SysUserService sysUserService;
    private final SysRoleUserService sysRoleUserService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", required = true),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段"),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)"),
            @Parameter(name = "username", description = "用户名"),
            @Parameter(name = "deptId", description = "部门ID")
    })
    @RequiresPermissions("sys:user:page")
    public Result<PageData<SysUserDto>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysUserDto> page = sysUserService.page(params);
        return new Result<PageData<SysUserDto>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @RequiresPermissions("sys:user:info")
    public Result<SysUserDto> get(@PathVariable("id") Long id) {
        SysUserDto data = sysUserService.get(id);
        //用户角色列表
        List<Long> roleIdList = sysRoleUserService.getRoleIdList(id);
        data.setRoleIdList(roleIdList);
        return new Result<SysUserDto>().ok(data);
    }

    @GetMapping("info")
    @Operation(summary = "登录用户信息")
    public Result<SysUserDto> info() {
        SysUserDto data = ConvertUtils.sourceToTarget(SecurityUser.getUser(), SysUserDto.class);
        return new Result<SysUserDto>().ok(data);
    }

    @PutMapping("password")
    @Operation(summary = "修改密码")
    @LogOperation("修改密码")
    public Result password(@RequestBody PasswordDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto);
        UserDetail user = SecurityUser.getUser();
        //原密码不正确
        if (!PasswordUtils.matches(dto.getPassword(), user.getPassword())) {
            return new Result().error(ErrorCode.PASSWORD_ERROR);
        }
        sysUserService.updatePassword(user.getId(), dto.getNewPassword());
        return new Result();
    }

    @PostMapping
    @Operation(summary = "保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:user:save")
    public Result save(@RequestBody SysUserDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        sysUserService.save(dto);
        return new Result();
    }

    @PutMapping
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:user:update")
    public Result update(@RequestBody SysUserDto dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        sysUserService.update(dto);
        return new Result();
    }

    @PutMapping("app")
    @Operation(summary = "修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:user:update")
    public Result updateUserInfo(@RequestBody SysUserDto dto) {
        sysUserService.updateUserInfo(dto);
        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");
        List<Long> idList = Arrays.asList(ids);
        if (idList.contains(SecurityUser.getUserId())) {
            throw new DefineException(ErrorCode.DEL_MYSELF_ERROR);
        }
        sysUserService.deleteBatchIds(idList);
        return new Result();
    }

    @GetMapping("export")
    @Operation(summary = "导出")
    @LogOperation("导出")
    @RequiresPermissions("sys:user:export")
    @Parameter(name = "username", description = "用户名")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysUserDto> list = sysUserService.list(params);
        ExcelUtils.exportExcelToTarget(response, null, "用户管理", list, SysUserExcel.class);
    }
}