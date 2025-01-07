package com.ronllan.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.sys.dao.SysDictDataDao;
import com.ronllan.modules.sys.dto.SysDictDataDto;
import com.ronllan.modules.sys.entity.SysDictDataEntity;
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
	
    @Override
    public PageData<SysDictDataDto> page(Map<String, Object> params) {
        return getPage(params, SysDictDataDto.class);
    }

	@Override
	public List<SysDictDataDto> list(Map<String, Object> params) {
		List<SysDictDataEntity> entityList = getObjectList(params);
        return ConvertUtils.sourceToTarget(entityList, SysDictDataDto.class);
	}
    
    @Override
    public SysDictDataDto get(Long id) {
        SysDictDataEntity entity = getObjectById(id);
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
    	deleteById(ids);
    }

}