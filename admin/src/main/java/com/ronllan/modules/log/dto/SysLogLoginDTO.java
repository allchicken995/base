package com.ronllan.modules.log.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ronllan.common.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "登录日志")
public class SysLogLoginDTO implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户操作  0：用户登录   1：用户退出")
    private Integer operation;

    @Schema(description = "状态  0：失败    1：成功    2：账号已锁定")
    private Integer status;

    @Schema(description = "用户代理")
    private String userAgent;

    @Schema(description = "操作IP")
    private String ip;

    @Schema(description = "用户名")
    private String creatorName;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createDate;

}
