package com.ronllan.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.sys.entity.SysIconEntity;

/**
 * 图标管理
 *
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface SysIconDao extends BaseDao<SysIconEntity> {
	
	/**
     * 图标列表
     */
    List<SysIconEntity> getList(Map<String, Object> params);
}
