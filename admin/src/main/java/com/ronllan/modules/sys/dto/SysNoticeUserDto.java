package com.ronllan.modules.sys.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ronllan.common.dto.BaseDto;
import com.ronllan.common.utils.DateUtils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 我的通知
 *
 * @author glq gugameds066@gmail.com
 */
@Data
@Schema(description = "通知管理")
public class SysNoticeUserDto extends BaseDto{
	
	@Schema(description = "通知ID")
    private Long noticeId;
	
	@Schema(description = "接收者ID")
    private Long receiverId;
	
	@Schema(description = "阅读状态  0：未读  1：已读")
	private Integer readStatus;
	
	@Schema(description = "阅读时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date readDate;
    
}