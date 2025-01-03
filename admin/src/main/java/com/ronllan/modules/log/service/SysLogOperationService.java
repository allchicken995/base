package com.ronllan.modules.log.service;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;

import java.util.List;
import java.util.Map;

import com.ronllan.modules.log.dto.SysLogOperationDto;
import com.ronllan.modules.log.entity.SysLogOperationEntity;

/**
 * 操作日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
public interface SysLogOperationService extends BaseService<SysLogOperationEntity> {

    PageData<SysLogOperationDto> page(Map<String, Object> params);

    List<SysLogOperationDto> list(Map<String, Object> params);

    void save(SysLogOperationEntity entity);
}