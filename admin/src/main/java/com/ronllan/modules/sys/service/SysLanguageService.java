package com.ronllan.modules.sys.service;

import com.ronllan.modules.sys.entity.SysLanguageEntity;

import com.ronllan.common.service.BaseService;


/**
 * 国际化
 *
 * @author glq gugameds066@gmail.com
 */
public interface SysLanguageService extends BaseService<SysLanguageEntity> {

    /**
     * 保存或更新
     * @param tableName   表名
     * @param tableId     表主键
     * @param fieldName   字段名
     * @param fieldValue  字段值
     * @param language    语言
     */
    void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language);

    /**
     * 删除国际化
     * @param tableName   表名
     * @param tableId     表主键
     */
    void deleteLanguage(String tableName, Long tableId);
}

