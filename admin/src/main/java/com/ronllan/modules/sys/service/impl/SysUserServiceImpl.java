package com.ronllan.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.user.UserDetail;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.security.dto.LoginDto;
import com.ronllan.modules.security.password.PasswordUtils;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dao.SysUserDao;
import com.ronllan.modules.sys.dto.SysUserDto;
import com.ronllan.modules.sys.entity.SysUserEntity;
import com.ronllan.modules.sys.enums.SuperAdminEnum;
import com.ronllan.modules.sys.service.SysDeptService;
import com.ronllan.modules.sys.service.SysRoleUserService;
import com.ronllan.modules.sys.service.SysUserService;

import lombok.AllArgsConstructor;


/**
 * 系统用户
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    private final SysRoleUserService sysRoleUserServiceImpl;
    private final SysDeptService sysDeptServiceImpl;

    @Override
    public PageData<SysUserDto> page(Map<String, Object> params) {
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            params.put("deptIdList", sysDeptServiceImpl.getSubDeptIdList(user.getDeptId()));
        }
        return getPage("getObjectList",params,SysUserDto.class);
    }

    @Override
    public List<SysUserDto> list(Map<String, Object> params) {
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            params.put("deptIdList", sysDeptServiceImpl.getSubDeptIdList(user.getDeptId()));
        }
        List<SysUserEntity> entityList = getObjectList("getObjectList",params);
        return ConvertUtils.sourceToTarget(entityList, SysUserDto.class);
    }

    @Override
    public SysUserDto get(Long id) {
        SysUserEntity entity = getObjectById(id);
        return ConvertUtils.sourceToTarget(entity, SysUserDto.class);
    }

    @Override
    public SysUserDto getLogin(LoginDto login) {
    	SysUserEntity entity = new SysUserEntity();
    	entity.setUsername(login.getUsername());
    	entity = getObject(entity);
        return ConvertUtils.sourceToTarget(entity, SysUserDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserDto dto) {
        SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);
        //密码加密
        String password = PasswordUtils.encode(entity.getPassword());
        entity.setPassword(password);
        //保存用户
        entity.setSuperAdmin(SuperAdminEnum.NO.value());
        insert(entity);
        //保存角色用户关系
        sysRoleUserServiceImpl.saveOrUpdate(entity.getId(), dto.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUserDto dto) {
        SysUserEntity entity = ConvertUtils.sourceToTarget(dto, SysUserEntity.class);
        //密码加密
        if (StringUtils.isBlank(dto.getPassword())) {
            entity.setPassword(null);
        } else {
            String password = PasswordUtils.encode(entity.getPassword());
            entity.setPassword(password);
        }
        //更新用户
        updateById(entity);
        //更新角色用户关系
        sysRoleUserServiceImpl.saveOrUpdate(entity.getId(), dto.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserInfo(SysUserDto dto) {
        SysUserEntity entity = getObjectById(dto.getId());
        entity.setHeadUrl(dto.getHeadUrl());
        entity.setRealName(dto.getRealName());
        entity.setMobile(dto.getMobile());
        entity.setEmail(dto.getEmail());
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
    	deleteById(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long id, String newPassword) {
        newPassword = PasswordUtils.encode(newPassword);
        SysUserEntity entity = new SysUserEntity();
        entity.setId(id);
        entity.setPassword(newPassword);
        updateById(entity);
    }

}
