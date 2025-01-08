package com.ronllan.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.sys.entity.SysRoleUserEntity;

/**
 * 角色用户关系
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface SysRoleUserDao extends BaseDao<SysRoleUserEntity> {
	
}