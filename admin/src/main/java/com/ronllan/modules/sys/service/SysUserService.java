package com.ronllan.modules.sys.service;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;

import java.util.List;
import java.util.Map;

import com.ronllan.modules.security.dto.LoginDto;
import com.ronllan.modules.sys.dto.SysUserDto;
import com.ronllan.modules.sys.entity.SysUserEntity;


/**
 * 系统用户
 * 
 * @author glq gugameds066@gmail.com
 */
public interface SysUserService extends BaseService<SysUserEntity> {

	PageData<SysUserDto> page(Map<String, Object> params);

	List<SysUserDto> list(Map<String, Object> params);

	SysUserDto get(Long id);

	SysUserDto getLogin(LoginDto login);

	void save(SysUserDto dto);

	void update(SysUserDto dto);

	void updateUserInfo(SysUserDto dto);

	void delete(Long[] ids);

	/**
	 * 修改密码
	 * @param id           用户ID
	 * @param newPassword  新密码
	 */
	void updatePassword(Long id, String newPassword);

}
