package com.ronllan.modules.sys.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.common.utils.TreeUtils;
import com.ronllan.modules.sys.dao.SysRoleMenuDao;
import com.ronllan.modules.sys.dto.SysRoleMenuDto;
import com.ronllan.modules.sys.entity.SysRoleMenuEntity;
import com.ronllan.modules.sys.service.SysRoleMenuService;

import cn.hutool.core.collection.CollUtil;


/**
 * 角色与菜单对应关系
 * 
 * @author glq gugameds066@gmail.com
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<SysRoleMenuDto> menuIdList) {
		//先删除角色菜单关系
		deleteByRoleIds(new Long[]{roleId});
		//角色没有一个菜单权限的情况
		if(CollUtil.isEmpty(menuIdList)){
			return ;
		}
		//保存角色菜单关系
		for(SysRoleMenuDto dto : menuIdList){
			SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
			sysRoleMenuEntity.setMenuId(dto.getMenuId());
			sysRoleMenuEntity.setRoleId(roleId);
			//保存
			insert(sysRoleMenuEntity);
		}
	}

	@Override
	public List<SysRoleMenuDto> getMenuRoleList(Long roleId){
		List<SysRoleMenuEntity> entityList = baseDao.getMenuRoleList(roleId);
		List<SysRoleMenuDto> dtoList = ConvertUtils.sourceToTarget(entityList, SysRoleMenuDto.class);
		for(SysRoleMenuDto dto : dtoList) {
			dto.setId(dto.getMenuId());
			dto.setPid(dto.getMenuPid());
		}
		return TreeUtils.build(dtoList);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByRoleIds(Long[] roleIds) {
		baseDao.deleteByRoleIds(roleIds);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByMenuId(Long menuId) {
		baseDao.deleteByMenuId(menuId);
	}

}