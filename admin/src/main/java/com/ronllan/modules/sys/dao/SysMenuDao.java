package com.ronllan.modules.sys.dao;

import com.ronllan.common.dao.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ronllan.modules.sys.entity.SysMenuEntity;

import java.util.List;

/**
 * 菜单管理
 * 
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {

	/**
	 * 查询所有菜单列表
	 *
	 * @param menuType 菜单类型
	 * @param language 语言
	 */
	List<SysMenuEntity> getMenuList(@Param("menuType") Integer menuType, @Param("language") String language);

	/**
	 * 查询管理员用户菜单列表
	 *
	 * @param language 语言
	 */
	List<SysMenuEntity> getAdminMenuList(@Param("language") String language);
	/**
	 * 查询用户菜单列表
	 *
	 * @param userId 用户ＩＤ
	 * @param menuType 菜单类型
	 * @param language 语言
	 */
	List<SysMenuEntity> getUserMenuList(@Param("userId") Long userId, @Param("menuType") Integer menuType, @Param("language") String language);

	/**
	 * 查询用户权限列表
	 * @param userId  用户ID
	 */
	List<String> getUserPermissionsList(Long userId);

	/**
	 * 查询所有权限列表
	 */
	List<String> getPermissionsList();

	/**
	 * 根据父菜单，查询子菜单
	 * @param pid  父菜单ID
	 */
	List<SysMenuEntity> getListPid(Long pid);

}
