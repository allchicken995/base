package com.ronllan.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.sys.entity.SysNoticeEntity;

import java.util.List;
import java.util.Map;

/**
* 通知管理
*
* @author glq gugameds066@gmail.com
*/
@Mapper
public interface SysNoticeDao extends BaseDao<SysNoticeEntity> {
    /**
     * 获取被通知的用户列表
     */
    List<SysNoticeEntity> getNoticeUserList(Map<String, Object> params);

    /**
     * 获取我的通知列表
     */
    List<SysNoticeEntity> getMyNoticeList(Map<String, Object> params);
}