package com.ronllan.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;
import com.ronllan.modules.sys.dto.SysDictDataDto;
import com.ronllan.modules.sys.entity.SysDictDataEntity;

/**
 * 数据字典
 *
 * @author glq gugameds066@gmail.com
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {

    PageData<SysDictDataDto> page(Map<String, Object> params);
    
    List<SysDictDataDto> list(Map<String, Object> params);

    SysDictDataDto get(Long id);

    void save(SysDictDataDto dto);

    void update(SysDictDataDto dto);

    void delete(Long[] ids);

}