package com.ronllan.modules.sys.service;

import com.ronllan.common.service.BaseService;

import java.util.List;

import com.ronllan.modules.sys.entity.SysRoleUserEntity;

/**
 * 角色用户关系
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
public interface SysRoleUserService extends BaseService<SysRoleUserEntity> {

    /**
     * 保存或修改
     * @param userId      用户ID
     * @param roleIdList  角色ID列表
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 角色ID列表
     * @param userId  用户ID
     */
    List<Long> getRoleIdList(Long userId);
}