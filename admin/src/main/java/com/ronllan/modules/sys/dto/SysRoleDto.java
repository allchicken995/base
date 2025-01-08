package com.ronllan.modules.sys.dto;

import java.util.List;

import com.ronllan.common.dto.BaseDto;
import com.ronllan.common.validator.group.DefaultGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 角色管理
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "角色管理")
public class SysRoleDto extends BaseDto{

    @Schema(description = "角色名称")
    @NotBlank(message = "{sysrole.name.require}", groups = DefaultGroup.class)
    private String name;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "菜单ID列表")
    private List<SysRoleMenuDto> menuRoleList;

    @Schema(description = "部门ID列表")
    private List<Long> deptIdList;

}