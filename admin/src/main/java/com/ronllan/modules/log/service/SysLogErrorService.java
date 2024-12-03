package com.ronllan.modules.log.service;


import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;

import java.util.List;
import java.util.Map;

import com.ronllan.modules.log.dto.SysLogErrorDTO;
import com.ronllan.modules.log.entity.SysLogErrorEntity;

/**
 * 异常日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface SysLogErrorService extends BaseService<SysLogErrorEntity> {

    PageData<SysLogErrorDTO> page(Map<String, Object> params);

    List<SysLogErrorDTO> list(Map<String, Object> params);

    void save(SysLogErrorEntity entity);

}