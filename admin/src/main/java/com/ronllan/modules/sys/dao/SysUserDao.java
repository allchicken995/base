package com.ronllan.modules.sys.dao;

import com.ronllan.common.dao.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ronllan.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

	List<SysUserEntity> getList(Map<String, Object> params);

	SysUserEntity getLogin(String username);
	
	int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

	/**
	 * 根据部门ID，查询用户数
	 */
	int getCountByDeptId(Long deptId);

	/**
	 * 根据部门ID,查询用户ID列表
	 */
	List<Long> getUserIdListByDeptId(List<Long> deptIdList);
}