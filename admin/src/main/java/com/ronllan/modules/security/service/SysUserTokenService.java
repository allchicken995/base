package com.ronllan.modules.security.service;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;
import com.ronllan.common.utils.Result;

import java.util.Map;

import com.ronllan.modules.security.entity.SysUserTokenEntity;
import com.ronllan.modules.sys.entity.SysOnlineEntity;

/**
 * 用户Token
 * 
 * @author Mark sunlightcs@gmail.com
 */
public interface SysUserTokenService extends BaseService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	Result createToken(Long userId);

	/**
	 * 退出
	 * @param userId  用户ID
	 */
	void logout(Long userId);

	/**
	 * 在线用户分页
	 */
	PageData<SysOnlineEntity> onlinePage(Map<String, Object> params);

}