package com.ronllan.modules.security.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.user.UserDetail;
import com.ronllan.modules.security.dao.SysUserTokenDao;
import com.ronllan.modules.security.entity.SysUserTokenEntity;
import com.ronllan.modules.security.service.ShiroService;
import com.ronllan.modules.sys.dao.SysMenuDao;
import com.ronllan.modules.sys.dao.SysRoleDataScopeDao;
import com.ronllan.modules.sys.dao.SysUserDao;
import com.ronllan.modules.sys.entity.SysUserEntity;
import com.ronllan.modules.sys.enums.SuperAdminEnum;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ShiroServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements ShiroService {
    private final SysMenuDao sysMenuDao;
    private final SysUserTokenDao sysUserTokenDao;
    private final SysRoleDataScopeDao sysRoleDataScopeDao;

    @Override
    public Set<String> getUserPermissions(UserDetail user) {
        //系统管理员，拥有最高权限
        List<String> permissionsList;
        if (user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            permissionsList = sysMenuDao.getPermissionsList();
        } else {
            permissionsList = sysMenuDao.getUserPermissionsList(user.getId());
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String permissions : permissionsList) {
            if (StringUtils.isBlank(permissions)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(permissions.trim().split(",")));
        }

        return permsSet;
    }

    @Override
    public SysUserTokenEntity getByToken(String token) {
        return sysUserTokenDao.getByToken(token);
    }

    @Override
    public SysUserEntity getUser(Long userId) {
        return getObjectById(userId);
    }

    @Override
    public List<Long> getDataScopeList(Long userId) {
        return sysRoleDataScopeDao.getDataScopeList(userId);
    }
}