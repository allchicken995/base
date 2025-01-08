package com.ronllan.modules.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.constant.Constant;
import com.ronllan.common.exception.DefineException;
import com.ronllan.common.exception.ErrorCode;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.user.UserDetail;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.common.utils.HttpContextUtils;
import com.ronllan.common.utils.TreeUtils;
import com.ronllan.modules.sys.dao.SysMenuDao;
import com.ronllan.modules.sys.dto.SysLanguageDto;
import com.ronllan.modules.sys.dto.SysMenuDto;
import com.ronllan.modules.sys.entity.SysMenuEntity;
import com.ronllan.modules.sys.enums.SuperAdminEnum;
import com.ronllan.modules.sys.service.SysLanguageService;
import com.ronllan.modules.sys.service.SysMenuService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
    private final SysLanguageService sysLanguageServiceImpl;

    @Override
    public SysMenuDto get(Long id) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put(Constant.ID, id);
    	params.put("language", HttpContextUtils.getLanguage());
        SysMenuEntity entity = getObject("getObject",params);
        SysMenuDto dto = ConvertUtils.sourceToTarget(entity, SysMenuDto.class);
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysMenuDto dto) {
        SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);
        //保存菜单
        insert(entity);
        SysLanguageDto sysLanguageDto = new SysLanguageDto();
        sysLanguageDto.setTableName("sys_menu");
        sysLanguageDto.setTableId(entity.getId());
        sysLanguageDto.setFieldName("name");
        sysLanguageDto.setFieldValue(entity.getName());
        sysLanguageDto.setLanguage(HttpContextUtils.getLanguage());
        sysLanguageServiceImpl.saveOrUpdate(sysLanguageDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysMenuDto dto) {
        SysMenuEntity entity = ConvertUtils.sourceToTarget(dto, SysMenuEntity.class);
        //上级菜单不能为自身
        if (entity.getId().equals(entity.getPid())) {
            throw new DefineException(ErrorCode.SUPERIOR_MENU_ERROR_0);
        }
        //更新菜单
        updateById(entity);
        SysLanguageDto sysLanguageDto = new SysLanguageDto();
        sysLanguageDto.setTableName("sys_menu");
        sysLanguageDto.setTableId(entity.getId());
        sysLanguageDto.setFieldName("name");
        sysLanguageDto.setFieldValue(entity.getName());
        sysLanguageDto.setLanguage(HttpContextUtils.getLanguage());
        sysLanguageServiceImpl.saveOrUpdate(sysLanguageDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        deleteById(id);
    }

    @Override
    public List<SysMenuDto> getAllMenuList(Integer menuType) {
        List<SysMenuEntity> menuList = baseDao.getMenuList(menuType, HttpContextUtils.getLanguage());
        List<SysMenuDto> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDto.class);
        return TreeUtils.build(dtoList, Constant.MENU_ROOT);
    }

    @Override
    public List<SysMenuDto> getUserMenuList(UserDetail user, Integer menuType) {
        List<SysMenuEntity> menuList;
        //系统管理员，拥有最高权限
        if (user.getSuperAdmin() == SuperAdminEnum.YES.value()) {
            menuList = baseDao.getAdminMenuList(HttpContextUtils.getLanguage());
        } else {
            menuList = baseDao.getUserMenuList(user.getId(), menuType, HttpContextUtils.getLanguage());
        }
        List<SysMenuDto> dtoList = ConvertUtils.sourceToTarget(menuList, SysMenuDto.class);
        return TreeUtils.build(dtoList);
    }

    @Override
    public List<SysMenuDto> getListPid(Long pid) {
        List<SysMenuEntity> menuList = baseDao.getListPid(pid);

        return ConvertUtils.sourceToTarget(menuList, SysMenuDto.class);
    }

}