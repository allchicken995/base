package com.ronllan.modules.sys.service;

import org.apache.ibatis.annotations.Param;

import com.ronllan.common.service.BaseService;
import com.ronllan.modules.sys.entity.SysNoticeUserEntity;

/**
 * 我的通知
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysNoticeUserService extends BaseService<SysNoticeUserEntity> {

//    /**
//     * 通知全部用户
//     */
//    void insertAllUser(SysNoticeUserEntity entity);
//
//    /**
//     * 标记我的通知为已读
//     * @param receiverId  接收者ID
//     * @param noticeId    通知ID
//     */
//    void updateReadStatus(@Param("receiverId") Long receiverId, @Param("noticeId") Long noticeId);
//
//
//    /**
//     * 未读的通知数
//     * @param receiverId  接收者ID
//     */
//    int getUnReadNoticeCount(Long receiverId);
}