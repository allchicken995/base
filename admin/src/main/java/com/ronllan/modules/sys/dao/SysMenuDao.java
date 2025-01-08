package com.ronllan.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.sys.entity.SysMenuEntity;

/**
 * 菜单管理
 * 
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
	
}
