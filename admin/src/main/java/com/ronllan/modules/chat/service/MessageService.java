package com.ronllan.modules.chat.service;

import com.ronllan.common.service.BaseService;
import com.ronllan.modules.chat.entity.MessageEntity;

import java.util.List;

/**
 * 消息处理
 *
 * @author xyj godlikexyj@gmail.com
 */
public interface MessageService extends BaseService<MessageEntity> {

    void save(MessageEntity messageEntity);

    List<MessageEntity> list(Long userId, Long contactId);

}
