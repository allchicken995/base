package com.ronllan.modules.sys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改密码
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "修改密码")
public class PasswordDto implements Serializable {

    @Schema(description = "原密码")
    @NotBlank(message = "{sysuser.password.require}")
    private String password;

    @Schema(description = "新密码")
    @NotBlank(message = "{sysuser.password.require}")
    private String newPassword;

}