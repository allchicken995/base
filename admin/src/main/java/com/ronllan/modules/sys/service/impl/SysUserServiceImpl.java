package com.ronllan.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ronllan.modules.security.password.PasswordUtils;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dao.SysUserDao;
import com.ronllan.modules.sys.dto.SysUserDto;
import com.ronllan.modules.sys.entity.SysUserEntity;
import com.ronllan.modules.sys.enums.SuperAdminEnum;
import com.ronllan.modules.sys.service.SysDeptService;
import com.ronllan.modules.sys.service.SysRoleUserService;
import com.ronllan.modules.sys.service.SysUserService;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.user.UserDetail;
import com.ronllan.common.utils.ConvertUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    private final SysRoleUserService sysRoleUserService;
    private final SysDeptService sysDeptService;

    @Override
    public PageData<SysUserDto> page(Map<String, Object> params) {
        //转换成like
        paramsToLike(params, "username");
        //分页
        IPage<SysUserEntity> page = getPage(params, "t1.create_date", false);
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
        }
        //查询
        List<SysUserEntity> list = baseDao.getList(params);
        return getPageData(page,list,SysUserDto.class);
    }

    @Override
    public List<SysUserDto> list(Map<String, Object> params) {
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
        }
        List<SysUserEntity> entityList = baseDao.getList(params);
        return ConvertUtils.sourceToTarget(entityList, SysUserDto.class);
    }

    @Override
    public SysUserDto get(Long id) {
        SysUserEntity entity = baseDao.getById(id);
        return ConvertUtils.sourceToTarget(entity, SysUserDto.class);
    }

    @Override
    public SysUserDto getByUsername(String username) {
        SysUserEntity entity = baseDao.getByUsername(username);
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
        sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
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
        sysRoleUserService.saveOrUpdate(entity.getId(), dto.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserInfo(SysUserDto dto) {
        SysUserEntity entity = selectById(dto.getId());
        entity.setHeadUrl(dto.getHeadUrl());
        entity.setRealName(dto.getRealName());
        entity.setMobile(dto.getMobile());
        entity.setEmail(dto.getEmail());
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除用户
        baseDao.deleteBatchIds(Arrays.asList(ids));
        //删除角色用户关系
        sysRoleUserService.deleteByUserIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long id, String newPassword) {
        newPassword = PasswordUtils.encode(newPassword);
        baseDao.updatePassword(id, newPassword);
    }

    @Override
    public int getCountByDeptId(Long deptId) {
        return baseDao.getCountByDeptId(deptId);
    }

    @Override
    public List<Long> getUserIdListByDeptId(List<Long> deptIdList) {
        return baseDao.getUserIdListByDeptId(deptIdList);
    }

}
