package com.ronllan.modules.chat.dao;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.chat.entity.ChatGroupEntity;
import com.ronllan.modules.chat.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 消息处理
 *
 * @author xyj godlikexyj@gmail.com
 */
@Mapper
public interface MessageDao extends BaseDao<MessageEntity> {

    /**
     * 根据ID获取群聊
     * @param id
     * @return
     */
    @Select("select id, owner_id, creator, create_date, updater, update_date from chat_group where logical_delete = 0 and id = #{id}")
    ChatGroupEntity getGroupById(Long id);

}
