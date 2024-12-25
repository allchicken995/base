package com.ronllan.modules.sys.service;


import java.util.List;
import java.util.Map;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;
import com.ronllan.modules.sys.dto.SysIconDto;
import com.ronllan.modules.sys.entity.SysIconEntity;


/**
 * 图标管理
 *
 * @author glq gugameds066@gmail.com
 */
public interface SysIconService extends BaseService<SysIconEntity> {

	PageData<SysIconDto> page(Map<String, Object> params);

	List<SysIconDto> list(Map<String, Object> params);

	SysIconDto get(Long id);

	Long save(SysIconDto dto);

	void update(SysIconDto dto);

	void delete(Long[] ids);
}
