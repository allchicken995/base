package com.ronllan.modules.sys.service.impl;

import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;

import org.springframework.stereotype.Service;

import com.ronllan.modules.sys.dao.SysLanguageDao;
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
    public void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language) {
        SysLanguageEntity select = new SysLanguageEntity();
        select.setTableName(tableName);
        select.setTableId(tableId);
        select.setFieldName(fieldName);
        select.setLanguage(language);
        //判断是否有数据
        SysLanguageEntity entity = getObject(select);
        if(entity==null) {
        	SysLanguageEntity insert = ConvertUtils.clone(select);
        	insert.setFieldValue(fieldValue);
        	insert(insert);
        }else {
        	SysLanguageEntity update = ConvertUtils.clone(select);
        	update.setId(select.getId());
        	update.setFieldValue(fieldValue);
        	updateById(update);
        }
    }
    
}