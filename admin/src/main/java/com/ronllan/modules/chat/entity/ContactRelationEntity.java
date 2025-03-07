package com.ronllan.modules.chat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ronllan.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 联系人管理
 *
 * @author xyj godlikexyj@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("chat_contact_relation")
public class ContactRelationEntity extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 联系人ID
     */
    private Long contactId;
    /**
     * 联系人关系
     */
    private Integer state;

}
