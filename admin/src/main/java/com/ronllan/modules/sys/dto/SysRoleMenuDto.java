package com.ronllan.modules.sys.dto;

import java.io.Serializable;

import com.ronllan.common.dto.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 角色菜单关系
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(description = "角色菜单关系")
public class SysRoleMenuDto extends BaseDto implements Serializable {

	@Schema(description = "菜单ID")
	private Long menuId;
	
	@Schema(description = "上级菜单ID")
	private Long menuPid;
}