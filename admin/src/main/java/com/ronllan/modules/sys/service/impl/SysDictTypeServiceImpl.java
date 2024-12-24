package com.ronllan.modules.sys.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.user.UserDetail;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dao.SysDictTypeDao;
import com.ronllan.modules.sys.dto.SysDictDataDto;
import com.ronllan.modules.sys.dto.SysDictTypeDto;
import com.ronllan.modules.sys.entity.SysDictTypeEntity;
import com.ronllan.modules.sys.enums.SuperAdminEnum;
import com.ronllan.modules.sys.service.SysDeptService;
import com.ronllan.modules.sys.service.SysDictDataService;
import com.ronllan.modules.sys.service.SysDictTypeService;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;

/**
 * 字典类型
 *
 * @author Mark sunlightcs@gmail.com
 */
@AllArgsConstructor
@Service
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeDao, SysDictTypeEntity> implements SysDictTypeService {
	private final SysDeptService sysDeptService;
	private final SysDictDataService sysDictDataService;

    @Override
    public PageData<SysDictTypeDto> page(Map<String, Object> params) {
    	//转换成like
        paramsToLike(params, "dictType");
        paramsToLike(params, "dictName");
        //分页
        IPage<SysDictTypeEntity> page = getPage(params, "t1.create_date", false);
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
        }
        //查询
        List<SysDictTypeEntity> list = baseDao.getList(params);
        return getPageData(page,list,SysDictTypeDto.class);
    }

    private QueryWrapper<SysDictTypeEntity> getWrapper(Map<String, Object> params) {
        String dictType = (String) params.get("dictType");
        String dictName = (String) params.get("dictName");
        QueryWrapper<SysDictTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(dictType), "dict_type", dictType);
        wrapper.like(StringUtils.isNotBlank(dictName), "dict_name", dictName);

        return wrapper;
    }

    @Override
    public SysDictTypeDto get(Long id) {
        SysDictTypeEntity entity = baseDao.getObjectById(id);
        return ConvertUtils.sourceToTarget(entity, SysDictTypeDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictTypeDto dto) {
        SysDictTypeEntity entity = ConvertUtils.sourceToTarget(dto, SysDictTypeEntity.class);
        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictTypeDto dto) {
        SysDictTypeEntity entity = ConvertUtils.sourceToTarget(dto, SysDictTypeEntity.class);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
    	for(Long id : ids) {
    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put("dictTypeId", id);
    		List<SysDictDataDto> dto = sysDictDataService.list(params);
    		if(CollectionUtil.isNotEmpty(dto)) {
    			List<Long> idList = dto.stream().map(o->o.getId()).collect(Collectors.toList());
    			sysDictDataService.deleteBatchIds(idList);
    		}
    		baseDao.deleteBatchIds(Arrays.asList(ids));
    	}
        deleteBatchIds(Arrays.asList(ids));
    }

}