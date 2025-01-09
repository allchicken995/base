package com.ronllan.modules.log.dto;

import java.io.Serializable;

import com.ronllan.common.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 操作日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "操作日志")
public class SysLogOperationDto extends BaseDto{

    @Schema(description = "用户操作")
    private String operation;

    @Schema(description = "请求URI")
    private String requestUri;

    @Schema(description = "请求方式")
    private String requestMethod;

    @Schema(description = "请求参数")
    private String requestParams;

    @Schema(description = "请求时长(毫秒)")
    private Integer requestTime;

    @Schema(description = "用户代理")
    private String userAgent;

    @Schema(description = "操作IP")
    private String ip;

    @Schema(description = "状态  0：失败   1：成功")
    private Integer status;

    @Schema(description = "用户名")
    private String creatorName;

}