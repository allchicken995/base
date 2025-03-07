package com.ronllan.modules.chat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ronllan.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 聊天群实体类
 *
 * @author xyj godlikexyj@gmail.com
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("chat_group")
public class ChatGroupEntity extends BaseEntity {
    /**
     * 群聊名称
     */
    private String groupName;
    /**
     * 群主id
     */
    private Long ownerId;
}
