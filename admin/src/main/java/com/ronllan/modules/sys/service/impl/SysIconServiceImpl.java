package com.ronllan.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.sys.dao.SysIconDao;
import com.ronllan.modules.sys.dto.SysIconDto;
import com.ronllan.modules.sys.entity.SysIconEntity;
import com.ronllan.modules.sys.service.SysIconService;

import lombok.AllArgsConstructor;

/**
 * 图标管理
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@Service
public class SysIconServiceImpl extends BaseServiceImpl<SysIconDao, SysIconEntity> implements SysIconService {
	
    @Override
    public PageData<SysIconDto> page(Map<String, Object> params) {
        return getPage(params,SysIconDto.class);
    }

    @Override
    public List<SysIconDto> list(Map<String, Object> params) {
        List<SysIconEntity> entityList = getObjectList(params);
        return ConvertUtils.sourceToTarget(entityList, SysIconDto.class);
    }

    @Override
    public SysIconDto get(Long id) {
    	SysIconEntity entity = getObjectById(id);
        return ConvertUtils.sourceToTarget(entity, SysIconDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(SysIconDto dto) {
    	SysIconEntity entity = ConvertUtils.sourceToTarget(dto, SysIconEntity.class);
        insert(entity);
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysIconDto dto) {
    	SysIconEntity entity = ConvertUtils.sourceToTarget(dto, SysIconEntity.class);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
    	deleteById(ids);
    }

}