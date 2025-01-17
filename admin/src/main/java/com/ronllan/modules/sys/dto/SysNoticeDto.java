package com.ronllan.modules.sys.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ronllan.common.dto.BaseDto;
import com.ronllan.common.utils.DateUtils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 通知管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@Schema(description = "通知管理")
public class SysNoticeDto extends BaseDto{
    
    @Schema(description = "标题")
    private String title;
    
    @Schema(description = "内容")
    private String content;
    
    @Schema(description = "接收者类型  0：全部  1：部门")
    private Integer receiverType;
    
    @Schema(description = "接收者ID，用逗号分开")
    private String receiverTypeIds;
    
    @Schema(description = "接收者ID列表")
    private List<Long> receiverTypeList;
    
    @Schema(description = "发送状态  0：草稿  1：已发布")
    private Integer status;
    
    @Schema(description = "发送者")
    private String senderName;
    
    @Schema(description = "发送时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date senderDate;  
    
    @Schema(description = "接收者")
    private String receiverName;
    
    @Schema(description = "阅读时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date readDate;
    
    @Schema(description = "阅读状态  0：未读  1：已读")
    private Integer readStatus;
    
}