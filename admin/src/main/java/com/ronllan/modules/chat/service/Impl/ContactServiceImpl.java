package com.ronllan.modules.chat.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.modules.chat.dao.ContactRelationDao;
import com.ronllan.modules.chat.entity.ContactRelationEntity;
import com.ronllan.modules.chat.service.ContactService;
import com.ronllan.modules.sys.dao.SysUserDao;
import com.ronllan.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl extends BaseServiceImpl<ContactRelationDao, ContactRelationEntity> implements ContactService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private ContactRelationDao contactRelationDao;

    @Override
    public void addContact(Long userId, Long contactId) {
        //校验添加用户是否存在
        SysUserEntity contactEntity = sysUserDao.selectById(contactId);
        if (contactEntity != null){
            //获取当前两个用户之间的关系
            Integer state = contactRelationDao.getStateById(userId, contactId);
            if (state != null){
                //更新联系人关系为好友
                LambdaUpdateWrapper<ContactRelationEntity> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(ContactRelationEntity::getUserId, userId)
                        .eq(ContactRelationEntity::getContactId, contactId)
                        .set(ContactRelationEntity::getState, 0);
                contactRelationDao.update(null, updateWrapper);
                updateWrapper.eq(ContactRelationEntity::getUserId, contactId)
                        .eq(ContactRelationEntity::getContactId, userId)
                        .set(ContactRelationEntity::getState, 0);
                contactRelationDao.update(null, updateWrapper);
            }else {
                //添加联系人为好友
                ContactRelationEntity user = new ContactRelationEntity();
                user.setUserId(userId);
                user.setContactId(contactId);
                contactRelationDao.insert(user);
                ContactRelationEntity contact = new ContactRelationEntity();
                contact.setUserId(userId);
                contact.setContactId(contactId);
                contactRelationDao.insert(contact);
            }
        }
    }

    @Override
    public List list(Long userId) {
        //获取联系人
        List<String> contactIdList = contactRelationDao.getContactList(userId);
        return contactIdList;
    }
}