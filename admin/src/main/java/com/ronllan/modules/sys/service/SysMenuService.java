package com.ronllan.modules.sys.service;

import com.ronllan.common.service.BaseService;
import com.ronllan.common.user.UserDetail;

import java.util.List;
import java.util.Map;

import com.ronllan.modules.sys.dto.SysMenuDto;
import com.ronllan.modules.sys.entity.SysMenuEntity;


/**
 * 菜单管理
 * 
 * @author glq gugameds066@gmail.com
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

	SysMenuDto get(Long id);

	void save(SysMenuDto dto);

	void update(SysMenuDto dto);

	void delete(Long id);

	/**
	 * 菜单列表
	 *
	 * @param params 查询条件
	 */
	List<SysMenuDto> list(Map<String, Object> params);

	/**
	 * 用户菜单列表
	 *
	 * @param user  用户
	 * @param type 菜单类型
	 */
	List<SysMenuDto> getUserMenuList(UserDetail user, Integer[] type);
	
	/**
	 * 查询用户权限列表
	 * @param userId  用户ID
	 */
	List<SysMenuDto> getUserPermissionsList(Long userId);

	/**
	 * 查询所有权限列表
	 */
	List<SysMenuDto> getPermissionsList();
}
