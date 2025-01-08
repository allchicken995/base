package com.ronllan.modules.security.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ronllan.common.user.UserDetail;
import com.ronllan.modules.security.entity.SysUserTokenEntity;
import com.ronllan.modules.security.service.ShiroService;
import com.ronllan.modules.security.service.SysUserTokenService;
import com.ronllan.modules.sys.dto.SysMenuDto;
import com.ronllan.modules.sys.entity.SysUserEntity;
import com.ronllan.modules.sys.enums.SuperAdminEnum;
import com.ronllan.modules.sys.service.SysMenuService;
import com.ronllan.modules.sys.service.SysRoleDataScopeService;
import com.ronllan.modules.sys.service.SysUserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ShiroServiceImpl implements ShiroService {
    private final SysMenuService sysMenuServiceImpl;
    private final SysUserService sysUserServiceImpl;
    private final SysUserTokenService sysUserTokenServiceImpl;
    private final SysRoleDataScopeService sysRoleDataScopeServiceImpl;

    @Override
    public Set<String> getUserPermissions(UserDetail user) {
        //系统管理员，拥有最高权限
        List<SysMenuDto> permissionsList;
        if (user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            permissionsList = sysMenuServiceImpl.getPermissionsList();
        } else {
            permissionsList = sysMenuServiceImpl.getUserPermissionsList(user.getId());
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (SysMenuDto dto : permissionsList) {
            if (StringUtils.isBlank(dto.getPermissions())) {
                continue;
            }
            permsSet.addAll(Arrays.asList(dto.getPermissions().trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity getByToken(String token) {
    	SysUserTokenEntity entity = new SysUserTokenEntity();
    	entity.setToken(token);
        return sysUserTokenServiceImpl.getObject(entity);
    }

    @Override
    public SysUserEntity getUser(Long userId) {
        return sysUserServiceImpl.getObjectById(userId);
    }

    @Override
    public List<Long> getDataScopeList(Long userId) {
        return sysRoleDataScopeServiceImpl.getDataScopeList(userId);
    }
}