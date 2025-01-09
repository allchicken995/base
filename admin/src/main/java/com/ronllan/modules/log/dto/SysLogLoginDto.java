package com.ronllan.modules.log.dto;

import java.io.Serializable;

import com.ronllan.common.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "登录日志")
public class SysLogLoginDto extends BaseDto{

    @Schema(description = "用户操作  0：用户登录   1：用户退出")
    private Integer operation;

    @Schema(description = "状态  0：失败    1：成功    2：账号已锁定")
    private Integer status;

    @Schema(description = "用户代理")
    private String userAgent;

    @Schema(description = "操作IP")
    private String ip;

}
