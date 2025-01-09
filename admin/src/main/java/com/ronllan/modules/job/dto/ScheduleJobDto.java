package com.ronllan.modules.job.dto;

import org.hibernate.validator.constraints.Range;

import com.ronllan.common.dto.BaseDto;
import com.ronllan.common.validator.group.DefaultGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 定时任务
 *
 * @author glq gugameds066@gmail.com
 */
@Data
@Tag(name = "定时任务")
public class ScheduleJobDto extends BaseDto{

    @Schema(description = "spring bean名称")
    @NotBlank(message = "{schedule.bean.require}", groups = DefaultGroup.class)
    private String beanName;

    @Schema(description = "参数")
    private String params;

    @Schema(description = "cron表达式")
    @NotBlank(message = "{schedule.cron.require}", groups = DefaultGroup.class)
    private String cronExpression;

    @Schema(description = "任务状态  0：暂停  1：正常")
    @Range(min = 0, max = 1, message = "{schedule.status.range}", groups = DefaultGroup.class)
    private Integer status;

    @Schema(description = "备注")
    private String remark;

}
