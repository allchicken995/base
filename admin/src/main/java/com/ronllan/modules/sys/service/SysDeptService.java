package com.ronllan.modules.sys.service;

import com.ronllan.common.service.BaseService;

import java.util.List;
import java.util.Map;

import com.ronllan.modules.sys.dto.SysDeptDto;
import com.ronllan.modules.sys.entity.SysDeptEntity;

/**
 * 部门管理
 * 
 * @author Mark sunlightcs@gmail.com
 */
public interface SysDeptService extends BaseService<SysDeptEntity> {

	List<SysDeptDto> list(Map<String, Object> params);

	SysDeptDto get(Long id);

	void save(SysDeptDto dto);

	void update(SysDeptDto dto);

	void delete(Long id);

	/**
	 * 根据部门ID，获取本部门及子部门ID列表
	 * @param id   部门ID
	 */
	List<Long> getSubDeptIdList(Long id);
}