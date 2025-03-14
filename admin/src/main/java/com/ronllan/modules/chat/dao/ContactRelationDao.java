package com.ronllan.modules.chat.dao;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.chat.entity.ContactRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 联系人关系处理
 *
 * @author xyj godlikexyj@gmail.com
 */

@Mapper
public interface ContactRelationDao extends BaseDao<ContactRelationEntity> {

    @Select("select stata from ${table} where user_id = #{userId} and contact_id = #{contactId}")
    Integer getStateById(Long userId, Long contactId);

    @Select("select contact_id from chat_contact_relation where logical_delete = 0 and user_id = #{userId}")
    List<String> getContactList(Long userId);

}
