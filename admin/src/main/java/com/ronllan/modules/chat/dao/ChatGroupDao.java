package com.ronllan.modules.chat.dao;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.chat.entity.ChatGroupEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 群聊
 *
 * @author xyj godlikexyj@gmail.com
 */

@Mapper
public interface ChatGroupDao extends BaseDao<ChatGroupEntity> {
}
