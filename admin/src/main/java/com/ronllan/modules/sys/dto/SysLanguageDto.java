package com.ronllan.modules.sys.dto;

import java.io.Serializable;

import com.ronllan.common.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 图标管理
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "国际化")
public class SysLanguageDto extends BaseDto{

    @Schema(description = "表名")
    private String tableName;

    @Schema(description = "表主键")
    private Long tableId;

    @Schema(description = "字段名")
    private String fieldName;
    
    @Schema(description = "字段值")
    private String fieldValue;

    @Schema(description = "语言")
    private String language;

}