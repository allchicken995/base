package com.ronllan.modules.chat.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.modules.chat.dao.MessageDao;
import com.ronllan.modules.chat.entity.ChatGroupEntity;
import com.ronllan.modules.chat.entity.MessageEntity;
import com.ronllan.modules.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 消息处理
 *
 * @author xyj godlikexyj@gmail.com
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<MessageDao, MessageEntity> implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    @Transactional
    public void save(MessageEntity messageEntity) {
        messageDao.insert(messageEntity);
    }

    @Override
    public List<MessageEntity> list(Long userId, Long contactId) {
        //根据contactId判断联系人是用户还是群聊
        ChatGroupEntity group = messageDao.getGroupById(contactId);
        if (group != null){
            //获取群聊全部消息
            LambdaQueryWrapper<MessageEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(MessageEntity::getReceiverId, contactId);
            return messageDao.selectList(queryWrapper);
        }
        //查询与联系人之间的所有单聊消息
        LambdaQueryWrapper<MessageEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MessageEntity::getSenderId, userId)
                .eq(MessageEntity::getReceiverId, contactId)
                .or()
                .eq(MessageEntity::getSenderId, contactId)
                .eq(MessageEntity::getReceiverId, userId);
        return messageDao.selectList(queryWrapper);
    }
}
