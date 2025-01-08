package com.ronllan.modules.sys.service.impl;

import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;

import org.springframework.stereotype.Service;

import com.ronllan.modules.sys.dao.SysLanguageDao;
import com.ronllan.modules.sys.dto.SysLanguageDto;
import com.ronllan.modules.sys.entity.SysLanguageEntity;
import com.ronllan.modules.sys.service.SysLanguageService;

/**
 * 国际化
 *
 * @author glq gugameds066@gmail.com
 */
@Service
public class SysLanguageServiceImpl extends BaseServiceImpl<SysLanguageDao, SysLanguageEntity> implements SysLanguageService {

    @Override
    public void saveOrUpdate(SysLanguageDto dto) {
        SysLanguageEntity select = ConvertUtils.sourceToTarget(dto, SysLanguageEntity.class);
        //判断是否有数据
        SysLanguageEntity entity = getObject(select);
        if(entity==null) {
        	SysLanguageEntity insert = ConvertUtils.clone(select);
        	insert.setFieldValue(dto.getFieldValue());
        	insert(insert);
        }else {
        	SysLanguageEntity update = ConvertUtils.clone(select);
        	update.setId(select.getId());
        	update.setFieldValue(dto.getFieldValue());
        	updateById(update);
        }
    }
    
}