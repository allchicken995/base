package com.ronllan.modules.version.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * 版本信息
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "版本信息")
public class VersionDto implements Serializable {

    @Schema(description = "版本号")
    @NotBlank(message = "{sysversion.version.require}")
    private String version;

    @Schema(description = "更新内容")
    @NotBlank(message = "{sysversion.content.require}")
    private String content;

    @Schema(description = "更新日期")
    @NotBlank(message = "{sysversion.date.require}")
    private String date;
}