package com.ronllan.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.user.UserDetail;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dao.SysRoleDao;
import com.ronllan.modules.sys.dto.SysRoleDto;
import com.ronllan.modules.sys.entity.SysRoleEntity;
import com.ronllan.modules.sys.enums.SuperAdminEnum;
import com.ronllan.modules.sys.service.SysDeptService;
import com.ronllan.modules.sys.service.SysRoleDataScopeService;
import com.ronllan.modules.sys.service.SysRoleMenuService;
import com.ronllan.modules.sys.service.SysRoleService;
import com.ronllan.modules.sys.service.SysRoleUserService;

import lombok.AllArgsConstructor;

/**
 * 角色
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
    private final SysRoleMenuService sysRoleMenuServiceImpl;
    private final SysRoleDataScopeService sysRoleDataScopeServiceImpl;
    private final SysRoleUserService sysRoleUserServiceImpl;
    private final SysDeptService sysDeptServiceImpl;

    @Override
    public PageData<SysRoleDto> page(Map<String, Object> params) {
    	//普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            List<Long> deptIdList = sysDeptServiceImpl.getSubDeptIdList(user.getDeptId());
            params.put("deptIdList", deptIdList);
        }
        return getPage("getObjectList",params, SysRoleDto.class);
    }

    @Override
    public List<SysRoleDto> list(Map<String, Object> params) {
    	//普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            List<Long> deptIdList = sysDeptServiceImpl.getSubDeptIdList(user.getDeptId());
            params.put("deptIdList", deptIdList);
        }
        List<SysRoleEntity> entityList = getObjectList("getObjectList",params);
        return ConvertUtils.sourceToTarget(entityList, SysRoleDto.class);
    }

    @Override
    public SysRoleDto get(Long id) {
        SysRoleEntity entity = getObjectById(id);
        return ConvertUtils.sourceToTarget(entity, SysRoleDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysRoleDto dto) {
        SysRoleEntity entity = ConvertUtils.sourceToTarget(dto, SysRoleEntity.class);
        //保存角色
        insert(entity);
        //保存角色菜单关系
        sysRoleMenuServiceImpl.saveOrUpdate(entity.getId(), dto.getMenuRoleList());
        //保存角色数据权限关系
        sysRoleDataScopeServiceImpl.saveOrUpdate(entity.getId(), dto.getDeptIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleDto dto) {
        SysRoleEntity entity = ConvertUtils.sourceToTarget(dto, SysRoleEntity.class);
        //更新角色
        updateById(entity);
        //更新角色菜单关系
        sysRoleMenuServiceImpl.saveOrUpdate(entity.getId(), dto.getMenuRoleList());
        //更新角色数据权限关系
        sysRoleDataScopeServiceImpl.saveOrUpdate(entity.getId(), dto.getDeptIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
    	deleteById(ids);
    }

}