package com.ronllan.modules.sys.service;

import java.util.Map;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;
import com.ronllan.modules.sys.dto.SysNoticeDto;
import com.ronllan.modules.sys.entity.SysNoticeEntity;

/**
 * 通知管理
 *
 * @author glq gugameds066@gmail.com
 */
public interface SysNoticeService extends BaseService<SysNoticeEntity> {

	PageData<SysNoticeDto> page(Map<String, Object> params);
	
//    /**
//     * 获取被通知的用户
//     */
//    PageData<SysNoticeDTO> getNoticeUserPage(Map<String, Object> params);
//
//    /**
//     * 获取我的通知列表
//     */
//    PageData<SysNoticeDTO> getMyNoticePage(Map<String, Object> params);
}