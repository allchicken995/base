package com.ronllan.modules.sys.service.impl;

import com.ronllan.common.service.impl.BaseServiceImpl;
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
        SysLanguageEntity entity = new SysLanguageEntity();
        entity.setTableName(tableName);
        entity.setTableId(tableId);
        entity.setFieldName(fieldName);
        entity.setFieldValue(fieldValue);
        entity.setLanguage(language);

        //判断是否有数据
        if(baseDao.getLanguage(entity) == null){
            baseDao.insert(entity);
        }else {
            baseDao.updateLanguage(entity);
        }
    }
    
}