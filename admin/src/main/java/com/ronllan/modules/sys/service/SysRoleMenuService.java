package com.ronllan.modules.sys.service;

import java.util.List;

import com.ronllan.common.service.BaseService;
import com.ronllan.modules.sys.dto.SysRoleMenuDto;
import com.ronllan.modules.sys.entity.SysRoleMenuEntity;


/**
 * 角色与菜单对应关系
 * 
 * @author glq gugameds066@gmail.com
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenuEntity> {

	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<SysRoleMenuDto> getMenuRoleList(Long roleId);

	/**
	 * 保存或修改
	 * @param roleId      角色ID
	 * @param menuIdList  菜单ID列表
	 */
	void saveOrUpdate(Long roleId, List<SysRoleMenuDto> menuIdList);

	/**
	 * 根据角色id，删除角色菜单关系
	 * @param roleIds 角色ids
	 */
	void deleteByRoleIds(Long[] roleIds);
}