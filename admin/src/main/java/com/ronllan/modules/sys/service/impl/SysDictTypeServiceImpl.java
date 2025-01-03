package com.ronllan.modules.sys.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.sys.dao.SysDictTypeDao;
import com.ronllan.modules.sys.dto.SysDictDataDto;
import com.ronllan.modules.sys.dto.SysDictTypeDto;
import com.ronllan.modules.sys.entity.SysDictTypeEntity;
import com.ronllan.modules.sys.service.SysDictDataService;
import com.ronllan.modules.sys.service.SysDictTypeService;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;

/**
 * 字典类型
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@Service
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeDao, SysDictTypeEntity> implements SysDictTypeService {
	private final SysDictDataService sysDictDataService;

    @Override
    public PageData<SysDictTypeDto> page(Map<String, Object> params) {
        //分页
        IPage<SysDictTypeEntity> page = getPage(params, "sysDictType.create_date", false);
        //查询
        List<SysDictTypeEntity> list = baseDao.getList(params);
        return getPageData(page,list,SysDictTypeDto.class);
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