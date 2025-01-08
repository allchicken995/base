package com.ronllan.modules.sys.dto;

import java.io.Serializable;

import com.ronllan.common.dto.BaseDto;
import com.ronllan.common.validator.group.DefaultGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 字典类型
 *
 * @author glq gugameds066@gmail.com
 */
@Data
@Schema(description = "字典类型")
public class SysDictTypeDto extends BaseDto{

    @Schema(description = "字典类型")
    @NotBlank(message = "{sysdict.type.require}", groups = DefaultGroup.class)
    private String dictType;

    @Schema(description = "字典名称")
    @NotBlank(message = "{sysdict.name.require}", groups = DefaultGroup.class)
    private String dictName;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "排序")
    @Min(value = 0, message = "{sort.number}", groups = DefaultGroup.class)
    private Integer sort;

    @Schema(description = "部门ID")
    @NotNull(message = "{sysdict.deptId.require}", groups = DefaultGroup.class)
    private Long deptId;
    
    @Schema(description = "部门名称")
    private String deptName;
}