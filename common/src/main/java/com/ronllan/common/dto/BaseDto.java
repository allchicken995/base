package com.ronllan.common.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ronllan.common.utils.DateUtils;
import com.ronllan.common.utils.TreeNode;
import com.ronllan.common.validator.group.AddGroup;
import com.ronllan.common.validator.group.UpdateGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

/**
 * 基础传输类
 *
 * @author glq gugameds066@gmail.com
 */
@Data
public abstract class BaseDto extends TreeNode implements Serializable {
	
	@Schema(description = "id")
    @Null(message = "{id.null}", groups = AddGroup.class)
    @NotNull(message = "{id.require}", groups = UpdateGroup.class)
    private Long id;
	
	@Schema(description = "创建时间")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createDate;
	
	@Schema(description = "逻辑删除")
    private Long logicalDelete;
    
}