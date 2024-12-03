package com.ronllan.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ronllan.common.utils.DateUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 在线用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class SysOnlineEntity implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 登录时间
     */
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date loginDate;
    /**
     * 超时时间
     */
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date ExpireDate;
}