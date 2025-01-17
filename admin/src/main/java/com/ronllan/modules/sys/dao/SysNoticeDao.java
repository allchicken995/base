package com.ronllan.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.sys.entity.SysNoticeEntity;

/**
* 通知管理
*
* @author glq gugameds066@gmail.com
*/
@Mapper
public interface SysNoticeDao extends BaseDao<SysNoticeEntity> {
	
}