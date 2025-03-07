package com.ronllan.modules.chat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ronllan.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息实体类
 *
 * @author xyj godlikexyj@gmail.com
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("chat_message")
public class MessageEntity extends BaseEntity {

    /**
     * 消息发送者ID
     */
    private Long senderId;
    /**
     * 消息接收者ID
     */
    private Long receiverId;
    /**
     * 会话类型（0：单聊 1：群聊）
     */
    private Integer chatType;
    /**
     * 消息类型（0：文本 1：图片 3：语音）
     */
    private Integer msgType;
    /**
     *  消息内容
     */
    private String content;

}
