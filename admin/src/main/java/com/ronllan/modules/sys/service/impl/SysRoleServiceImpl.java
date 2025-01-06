package com.ronllan.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dao.SysRoleDao;
import com.ronllan.modules.sys.dto.SysRoleDto;
import com.ronllan.modules.sys.entity.SysRoleEntity;
import com.ronllan.modules.sys.enums.SuperAdminEnum;
import com.ronllan.modules.sys.service.*;

import com.ronllan.common.constant.Constant;
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
 * 角色
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
    private final SysRoleMenuService sysRoleMenuService;
    private final SysRoleDataScopeService sysRoleDataScopeService;
    private final SysRoleUserService sysRoleUserService;
    private final SysDeptService sysDeptService;

    @Override
    public PageData<SysRoleDto> page(Map<String, Object> params) {
        IPage<SysRoleEntity> page = baseDao.selectPage(
                getPage(params, Constant.CREATE_DATE, false),
                getWrapper(params)
        );

        return getPageData(page, SysRoleDto.class);
    }

    @Override
    public List<SysRoleDto> list(Map<String, Object> params) {
        List<SysRoleEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysRoleDto.class);
    }

    private QueryWrapper<SysRoleEntity> getWrapper(Map<String, Object> params) {
        String name = (String) params.get("name");

        QueryWrapper<SysRoleEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name);

        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            List<Long> deptIdList = sysDeptService.getSubDeptIdList(user.getDeptId());
            wrapper.in(deptIdList != null, "dept_id", deptIdList);
        }

        return wrapper;
    }

    @Override
    public SysRoleDto get(Long id) {
        SysRoleEntity entity = baseDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, SysRoleDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysRoleDto dto) {
        SysRoleEntity entity = ConvertUtils.sourceToTarget(dto, SysRoleEntity.class);
        //保存角色
        insert(entity);
        //保存角色菜单关系
        sysRoleMenuService.saveOrUpdate(entity.getId(), dto.getMenuRoleList());
        //保存角色数据权限关系
        sysRoleDataScopeService.saveOrUpdate(entity.getId(), dto.getDeptIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleDto dto) {
        SysRoleEntity entity = ConvertUtils.sourceToTarget(dto, SysRoleEntity.class);
        //更新角色
        updateById(entity);
        //更新角色菜单关系
        sysRoleMenuService.saveOrUpdate(entity.getId(), dto.getMenuRoleList());
        //更新角色数据权限关系
        sysRoleDataScopeService.saveOrUpdate(entity.getId(), dto.getDeptIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
        //删除角色
        baseDao.deleteBatchIds(Arrays.asList(ids));

        //删除角色用户关系
        sysRoleUserService.deleteByRoleIds(ids);

        //删除角色菜单关系
        sysRoleMenuService.deleteByRoleIds(ids);

        //删除角色数据权限关系
//        sysRoleDataScopeService.deleteByRoleIds(ids);
    }

}