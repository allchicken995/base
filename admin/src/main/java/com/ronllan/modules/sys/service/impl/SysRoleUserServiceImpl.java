package com.ronllan.modules.sys.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.modules.sys.dao.SysRoleUserDao;
import com.ronllan.modules.sys.entity.SysRoleUserEntity;
import com.ronllan.modules.sys.service.SysRoleUserService;

import cn.hutool.core.collection.CollUtil;

/**
 * 角色用户关系
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
@Service
public class SysRoleUserServiceImpl extends BaseServiceImpl<SysRoleUserDao, SysRoleUserEntity> implements SysRoleUserService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除角色用户关系
        SysRoleUserEntity entity = new SysRoleUserEntity();
		entity.setUserId(userId);
		List<SysRoleUserEntity> entityList = getObjectList(entity);
		if(CollUtil.isNotEmpty(entityList)) {
			for(SysRoleUserEntity temp : entityList) {
				deleteById(temp.getId());
			}
		}
        //用户没有一个角色权限的情况
        if(CollUtil.isNotEmpty(roleIdList)){
        	//保存角色用户关系
            for(Long roleId : roleIdList){
                SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
                sysRoleUserEntity.setUserId(userId);
                sysRoleUserEntity.setRoleId(roleId);
                //保存
                insert(sysRoleUserEntity);
            }
        }
    }

    @Override
    public List<Long> getRoleIdList(Long userId) {
    	SysRoleUserEntity entity = new SysRoleUserEntity();
    	entity.setUserId(userId);
    	List<SysRoleUserEntity> entityList = getObjectList(entity);
		if(CollUtil.isEmpty(entityList)){
    		return null;
    	}else {
    		return entityList.stream().map(SysRoleUserEntity::getRoleId).collect(Collectors.toList());
    	}
    }
}