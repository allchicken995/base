package com.ronllan.modules.log.service;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;

import java.util.List;
import java.util.Map;

import com.ronllan.modules.log.dto.SysLogLoginDto;
import com.ronllan.modules.log.entity.SysLogLoginEntity;

/**
 * 登录日志
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
public interface SysLogLoginService extends BaseService<SysLogLoginEntity> {

    PageData<SysLogLoginDto> page(Map<String, Object> params);

    List<SysLogLoginDto> list(Map<String, Object> params);

    void save(SysLogLoginDto dto);
}