package com.ronllan.modules.sys.service;

import com.ronllan.common.service.BaseService;

import java.util.List;

import com.ronllan.modules.sys.entity.SysRoleDataScopeEntity;

/**
 * 角色数据权限
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
public interface SysRoleDataScopeService extends BaseService<SysRoleDataScopeEntity> {

    /**
     * 根据角色ID，获取部门ID列表
     */
    List<Long> getDeptIdList(Long roleId);

    /**
     * 保存或修改
     * @param roleId      角色ID
     * @param deptIdList  部门ID列表
     */
    void saveOrUpdate(Long roleId, List<Long> deptIdList);
    
    /**
     * 获取用户对应的部门数据权限
     * @param userId  用户ID
     * @return        返回部门ID列表
     */
    List<Long> getDataScopeList(Long userId);
}