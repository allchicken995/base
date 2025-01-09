package com.ronllan.modules.job.dto;

import com.ronllan.common.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 定时任务日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "定时任务日志")
public class ScheduleJobLogDto extends BaseDto{

    @Schema(description = "任务id")
    private Long jobId;

    @Schema(description = "spring bean名称")
    private String beanName;

    @Schema(description = "参数")
    private String params;

    @Schema(description = "任务状态    0：失败    1：成功")
    private Integer status;

    @Schema(description = "失败信息")
    private String error;

    @Schema(description = "耗时(单位：毫秒)")
    private Integer times;

}
