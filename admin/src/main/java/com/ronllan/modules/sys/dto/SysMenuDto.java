package com.ronllan.modules.sys.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Range;

import com.ronllan.common.dto.BaseDto;
import com.ronllan.common.validator.group.DefaultGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 菜单管理
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "菜单管理")
public class SysMenuDto extends BaseDto{

    @Schema(description = "上级ID")
    @NotNull(message = "{sysmenu.pid.require}", groups = DefaultGroup.class)
    private Long pid;

    @Schema(description = "菜单名称")
    @NotBlank(message = "{sysmenu.name.require}", groups = DefaultGroup.class)
    private String name;

    @Schema(description = "菜单URL")
    private String url;

    @Schema(description = "类型  0：菜单   1：按钮")
    @Range(min = 0, max = 1, message = "{sysmenu.type.range}", groups = DefaultGroup.class)
    private Integer menuType;

    @Schema(description = "打开方式   0：内部   1：外部")
    @Range(min = 0, max = 1, message = "{sysmenu.openstyle.range}", groups = DefaultGroup.class)
    private Integer openStyle;

    @Schema(description = "正常图标")
    private String normalIcon;

    @Schema(description = "'选中图标")
    private String selectedIcon;

    @Schema(description = "授权(多个用逗号分隔，如：sys:user:list,sys:user:save)")
    private String permissions;

    @Schema(description = "排序")
    @Min(value = 0, message = "{sort.number}", groups = DefaultGroup.class)
    private Integer sort;

    @Schema(description = "上级菜单名称")
    private String parentName;
    
}