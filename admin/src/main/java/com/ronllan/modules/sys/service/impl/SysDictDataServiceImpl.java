package com.ronllan.modules.sys.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import com.ronllan.modules.sys.dao.SysDictDataDao;
import com.ronllan.modules.sys.dto.SysDictDataDto;
import com.ronllan.modules.sys.entity.SysDictDataEntity;
import com.ronllan.modules.sys.enums.SuperAdminEnum;
import com.ronllan.modules.sys.service.SysDeptService;
import com.ronllan.modules.sys.service.SysDictDataService;

import lombok.AllArgsConstructor;

/**
 * 字典类型
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@Service
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictDataDao, SysDictDataEntity> implements SysDictDataService {
	private final SysDeptService sysDeptService;
	
    @Override
    public PageData<SysDictDataDto> page(Map<String, Object> params) {
        IPage<SysDictDataEntity> page = baseDao.selectPage(
            getPage(params, "sort", true),
            getWrapper(params)
        );
        return getPageData(page, SysDictDataDto.class);
    }

    private QueryWrapper<SysDictDataEntity> getWrapper(Map<String, Object> params){
        String dictTypeId = (String) params.get("dictTypeId");
        String dictLabel = (String) params.get("dictLabel");
        String dictValue = (String) params.get("dictValue");
        QueryWrapper<SysDictDataEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type_id", Long.parseLong(dictTypeId));
        wrapper.like(StringUtils.isNotBlank(dictLabel), "dict_label", dictLabel);
        wrapper.like(StringUtils.isNotBlank(dictValue), "dict_value", dictValue);
        return wrapper;
    }

	@Override
	public List<SysDictDataDto> list(Map<String, Object> params) {
		//普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            params.put("deptIdList", sysDeptService.getSubDeptIdList(user.getDeptId()));
        }
		List<SysDictDataEntity> entityList = baseDao.getList(params);
        return ConvertUtils.sourceToTarget(entityList, SysDictDataDto.class);
	}
    
    @Override
    public SysDictDataDto get(Long id) {
        SysDictDataEntity entity = baseDao.selectById(id);
        return ConvertUtils.sourceToTarget(entity, SysDictDataDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictDataDto dto) {
        SysDictDataEntity entity = ConvertUtils.sourceToTarget(dto, SysDictDataEntity.class);
        insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictDataDto dto) {
        SysDictDataEntity entity = ConvertUtils.sourceToTarget(dto, SysDictDataEntity.class);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
//        deleteBatchIds(Arrays.asList(ids));
    }

}