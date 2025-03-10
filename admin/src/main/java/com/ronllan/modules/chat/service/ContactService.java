package com.ronllan.modules.chat.service;

import com.ronllan.common.service.BaseService;
import com.ronllan.modules.chat.entity.ContactRelationEntity;

import java.util.List;

/**
 * 联系人关系处理
 *
 * @author xyj godlikexyj@gmail.com
 */
public interface ContactService extends BaseService<ContactRelationEntity> {

    void addContact(Long userId, Long contactId);

    List list(Long userId);

}
