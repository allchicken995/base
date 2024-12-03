/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.ronllan.modules.sys.service;

import java.util.Map;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;
import com.ronllan.modules.sys.dto.SysDictTypeDto;
import com.ronllan.modules.sys.entity.SysDictTypeEntity;

/**
 * 数据字典
 *
 * @author glq gugameds066@gmail.com
 */
public interface SysDictTypeService extends BaseService<SysDictTypeEntity> {

    PageData<SysDictTypeDto> page(Map<String, Object> params);

    SysDictTypeDto get(Long id);

    void save(SysDictTypeDto dto);

    void update(SysDictTypeDto dto);

    void delete(Long[] ids);

}