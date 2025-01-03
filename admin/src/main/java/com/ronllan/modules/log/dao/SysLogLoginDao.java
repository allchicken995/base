package com.ronllan.modules.log.dao;

import com.ronllan.common.dao.BaseDao;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.modules.log.entity.SysLogLoginEntity;

/**
 * 登录日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Mapper
public interface SysLogLoginDao extends BaseDao<SysLogLoginEntity> {
	
}
