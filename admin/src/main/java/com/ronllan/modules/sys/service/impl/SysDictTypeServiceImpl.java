package com.ronllan.modules.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.sys.dao.SysDictTypeDao;
import com.ronllan.modules.sys.dto.SysDictTypeDto;
import com.ronllan.modules.sys.entity.SysDictTypeEntity;
import com.ronllan.modules.sys.service.SysDictTypeService;

import lombok.AllArgsConstructor;

/**
 * 字典类型
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@Service
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeDao, SysDictTypeEntity> implements SysDictTypeService {

    @Override
    public PageData<SysDictTypeDto> page(Map<String, Object> params) {
        //分页
        IPage<SysDictTypeEntity> page = getPage(params, "create_date", false);
        //查询
        List<SysDictTypeEntity> list = baseDao.getList(params);
//        List<SysDictTypeEntity> list = getObjectList(params);
        return getPageData(page,list,SysDictTypeDto.class);
    }

    @Override
    public SysDictTypeDto get(Long id) {
    	SysDictTypeEntity entity = getObjectById(id);
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
    	deleteById(ids);
    }

}