package com.ronllan.modules.sys.service;


import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;

import java.util.List;
import java.util.Map;

import com.ronllan.modules.sys.dto.SysRoleDto;
import com.ronllan.modules.sys.entity.SysRoleEntity;


/**
 * 角色
 * 
 * @author glq gugameds066@gmail.com
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

	PageData<SysRoleDto> page(Map<String, Object> params);

	List<SysRoleDto> list(Map<String, Object> params);

	SysRoleDto get(Long id);

	void save(SysRoleDto dto);

	void update(SysRoleDto dto);

	void delete(Long[] ids);

}
