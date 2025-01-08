package com.ronllan.modules.sys.service;

import com.ronllan.modules.sys.dto.SysLanguageDto;
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
     * 
     * @param dto 目标对象
     */
    void saveOrUpdate(SysLanguageDto dto);
    
}

