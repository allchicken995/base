package com.ronllan.modules.sys.dao;

import com.ronllan.common.dao.BaseDao;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色与菜单对应关系
 * 
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {

	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<SysRoleMenuEntity> getMenuRoleList(Long roleId);

	/**
	 * 根据角色id，删除角色菜单关系
	 * @param roleIds 角色ids
	 */
	void deleteByRoleIds(Long[] roleIds);
}
