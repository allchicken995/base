package com.ronllan.modules.sys.service;

import com.ronllan.common.service.BaseService;
import com.ronllan.modules.sys.dto.SysNoticeUserDto;
import com.ronllan.modules.sys.entity.SysNoticeUserEntity;

/**
 * 我的通知
 *
 * @author glq gugameds066@gmail.com
 */
public interface SysNoticeUserService extends BaseService<SysNoticeUserEntity> {

    void save(SysNoticeUserDto dto);

    void update(SysNoticeUserDto dto);

    void delete(Long[] ids);
    
}