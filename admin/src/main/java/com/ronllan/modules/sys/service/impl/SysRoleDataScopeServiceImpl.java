package com.ronllan.modules.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.modules.sys.dao.SysRoleDataScopeDao;
import com.ronllan.modules.sys.entity.SysRoleDataScopeEntity;
import com.ronllan.modules.sys.service.SysRoleDataScopeService;

import cn.hutool.core.collection.CollUtil;

/**
 * 角色数据权限
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Service
public class SysRoleDataScopeServiceImpl extends BaseServiceImpl<SysRoleDataScopeDao, SysRoleDataScopeEntity>
        implements SysRoleDataScopeService {

    @Override
    public List<Long> getDeptIdList(Long roleId) {
    	SysRoleDataScopeEntity entity = new SysRoleDataScopeEntity();
    	entity.setRoleId(roleId);
    	List<SysRoleDataScopeEntity> entityList = getObjectList(entity);
    	if(CollUtil.isEmpty(entityList)){
    		return null;
    	}else {
    		return entityList.stream().map(SysRoleDataScopeEntity::getDeptId).collect(Collectors.toList());
    	}
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        //先删除角色数据权限关系
    	SysRoleDataScopeEntity entity = new SysRoleDataScopeEntity();
		entity.setRoleId(roleId);
		List<SysRoleDataScopeEntity> entityList = getObjectList(entity);
		if(CollUtil.isNotEmpty(entityList)) {
			for(SysRoleDataScopeEntity temp : entityList) {
				deleteById(temp.getId());
			}
		}
        //角色没有一个数据权限的情况
        if(CollUtil.isNotEmpty(deptIdList)){
        	//保存角色数据权限关系
            for(Long deptId : deptIdList){
                SysRoleDataScopeEntity sysRoleDataScopeEntity = new SysRoleDataScopeEntity();
                sysRoleDataScopeEntity.setDeptId(deptId);
                sysRoleDataScopeEntity.setRoleId(roleId);
                //保存
                insert(sysRoleDataScopeEntity);
            }
        }
    }
    
    @Override
    public List<Long> getDataScopeList(Long userId) {
    	Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId",userId);
		List<SysRoleDataScopeEntity> entityList = getObjectList("getDataScopeList",params);
		if(CollUtil.isEmpty(entityList)){
    		return null;
    	}else {
    		return entityList.stream().map(SysRoleDataScopeEntity::getDeptId).collect(Collectors.toList());
    	}
    }
    
}