package com.ronllan.modules.log.service;


import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;

import java.util.List;
import java.util.Map;

import com.ronllan.modules.log.dto.SysLogErrorDto;
import com.ronllan.modules.log.entity.SysLogErrorEntity;

/**
 * 异常日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
public interface SysLogErrorService extends BaseService<SysLogErrorEntity> {

    PageData<SysLogErrorDto> page(Map<String, Object> params);

    List<SysLogErrorDto> list(Map<String, Object> params);

    void save(SysLogErrorEntity entity);

}