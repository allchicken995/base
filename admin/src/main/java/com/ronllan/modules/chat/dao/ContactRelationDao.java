package com.ronllan.modules.chat.dao;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.chat.entity.ContactRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 联系人关系处理
 *
 * @author xyj godlikexyj@gmail.com
 */

@Mapper
public interface ContactRelationDao extends BaseDao<ContactRelationEntity> {

    @Select("select stata from ${table} where user_id = #{userId} and contact_id = #{contactId}")
    Integer getStateById(Long userId, Long contactId);

}
