package com.ronllan.modules.sys.dto;

import com.ronllan.common.dto.BaseDto;
import com.ronllan.common.validator.group.DefaultGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 图标管理
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "图标管理")
public class SysIconDto extends BaseDto{

    @Schema(description = "图标名称")
    @NotBlank(message = "{sysicon.name.require}", groups = DefaultGroup.class)
    private String name;

    @Schema(description = "正常图标")
    private String normalIcon;

    @Schema(description = "选中图标")
    private String selectedIcon;

    @Schema(description = "排序")
    @Min(value = 0, message = "{sort.number}", groups = DefaultGroup.class)
    private Integer sort;

}