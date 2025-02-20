package com.ronllan.modules.version.service;

import java.util.List;
import java.util.Map;

import com.ronllan.modules.version.dto.VersionDto;


/**
 * 版本管理
 * 
 * @author glq gugameds066@gmail.com
 */
public interface VersionService{

	List<VersionDto> list(Map<String, Object> params);

}
