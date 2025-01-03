package com.ronllan.modules.job.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ronllan.common.utils.DateUtils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "定时任务日志")
public class ScheduleJobLogDto implements Serializable {

    @Schema(description = "id")
    private Long id;

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

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createDate;

}
