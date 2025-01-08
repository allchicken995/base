package com.ronllan.modules.security.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.security.entity.SysUserTokenEntity;

/**
 * 系统用户Token
 * 
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {
	
}
