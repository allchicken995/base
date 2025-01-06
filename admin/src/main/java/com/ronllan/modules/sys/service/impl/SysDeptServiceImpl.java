package com.ronllan.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.constant.Constant;
import com.ronllan.common.exception.DefineException;
import com.ronllan.common.exception.ErrorCode;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.user.UserDetail;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.common.utils.TreeUtils;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dao.SysDeptDao;
import com.ronllan.modules.sys.dto.SysDeptDto;
import com.ronllan.modules.sys.entity.SysDeptEntity;
import com.ronllan.modules.sys.enums.SuperAdminEnum;
import com.ronllan.modules.sys.service.SysDeptService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

    @Override
    public List<SysDeptDto> list(Map<String, Object> params) {
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            params.put("deptIdList", getSubDeptIdList(user.getDeptId()));
        }

        //查询部门列表
        List<SysDeptEntity> entityList = baseDao.getList(params);

        List<SysDeptDto> dtoList = ConvertUtils.sourceToTarget(entityList, SysDeptDto.class);

        return TreeUtils.build(dtoList);
    }

    @Override
    public SysDeptDto get(Long id) {
        //超级管理员，部门ID为null
        if (id == null) {
            return null;
        }

        SysDeptEntity entity = baseDao.getById(id);

        return ConvertUtils.sourceToTarget(entity, SysDeptDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDeptDto dto) {
        SysDeptEntity entity = ConvertUtils.sourceToTarget(dto, SysDeptEntity.class);

        entity.setPids(getPidList(entity.getPid()));
        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDeptDto dto) {
        SysDeptEntity entity = ConvertUtils.sourceToTarget(dto, SysDeptEntity.class);
        //上级部门不能为自身
        if (entity.getId().equals(entity.getPid())) {
            throw new DefineException(ErrorCode.SUPERIOR_DEPT_ERROR_0);
        }
        //上级部门不能为下级部门
        List<Long> subDeptList = getSubDeptIdList(entity.getId());
        if (subDeptList.contains(entity.getPid())) {
            throw new DefineException(ErrorCode.SUPERIOR_DEPT_ERROR_0);
        }
        entity.setPids(getPidList(entity.getPid()));
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        delete(id);
    }

    @Override
    public List<Long> getSubDeptIdList(Long id) {
        List<Long> deptIdList = baseDao.getSubDeptIdList("%" + id + "%");
        deptIdList.add(id);

        return deptIdList;
    }

    /**
     * 获取所有上级部门ID
     * @param pid 上级ID
     */
    private String getPidList(Long pid) {
        //顶级部门，无上级部门
        if (Constant.DEPT_ROOT.equals(pid)) {
            return Constant.DEPT_ROOT + "";
        }

        //所有部门的id、pid列表
        List<SysDeptEntity> deptList = baseDao.getIdAndPidList();

        //list转map
        Map<Long, SysDeptEntity> map = new HashMap<>(deptList.size());
        for (SysDeptEntity entity : deptList) {
            map.put(entity.getId(), entity);
        }

        //递归查询所有上级部门ID列表
        List<Long> pidList = new ArrayList<>();
        getPidTree(pid, map, pidList);

        return StringUtils.join(pidList, ",");
    }

    private void getPidTree(Long pid, Map<Long, SysDeptEntity> map, List<Long> pidList) {
        //顶级部门，无上级部门
        if (Constant.DEPT_ROOT.equals(pid)) {
            return;
        }

        //上级部门存在
        SysDeptEntity parent = map.get(pid);
        if (parent != null) {
            getPidTree(parent.getPid(), map, pidList);
        }

        pidList.add(pid);
    }
}
