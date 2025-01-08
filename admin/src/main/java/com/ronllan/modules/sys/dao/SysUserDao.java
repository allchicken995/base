package com.ronllan.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.sys.entity.SysUserEntity;

/**
 * 系统用户
 * 
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {
	
}