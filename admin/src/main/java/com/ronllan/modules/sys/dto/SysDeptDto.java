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
 * 部门管理
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "部门管理")
public class SysDeptDto extends BaseDto{

    @Schema(description = "上级ID")
    @NotNull(message = "{sysdept.pid.require}", groups = DefaultGroup.class)
    private Long pid;

    @Schema(description = "部门名称")
    @NotBlank(message = "{sysdept.name.require}", groups = DefaultGroup.class)
    private String name;

    @Schema(description = "负责人ID")
    private Long leaderId;

    @Schema(description = "负责人名称")
    private String leaderName;

    @Schema(description = "排序")
    @Min(value = 0, message = "{sort.number}", groups = DefaultGroup.class)
    private Integer sort;

    @Schema(description = "上级部门名称")
    private String parentName;
    
}