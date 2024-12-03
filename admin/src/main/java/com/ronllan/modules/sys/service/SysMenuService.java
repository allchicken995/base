package com.ronllan.modules.sys.service;

import com.ronllan.common.service.BaseService;
import com.ronllan.common.user.UserDetail;

import java.util.List;

import com.ronllan.modules.sys.dto.SysMenuDto;
import com.ronllan.modules.sys.entity.SysMenuEntity;


/**
 * 菜单管理
 * 
 * @author Mark sunlightcs@gmail.com
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

	SysMenuDto get(Long id);

	void save(SysMenuDto dto);

	void update(SysMenuDto dto);

	void delete(Long id);

	/**
	 * 菜单列表
	 *
	 * @param type 菜单类型
	 */
	List<SysMenuDto> getAllMenuList(Integer type);

	/**
	 * 用户菜单列表
	 *
	 * @param user  用户
	 * @param type 菜单类型
	 */
	List<SysMenuDto> getUserMenuList(UserDetail user, Integer type);

	/**
	 * 根据父菜单，查询子菜单
	 * @param pid  父菜单ID
	 */
	List<SysMenuDto> getListPid(Long pid);
}
