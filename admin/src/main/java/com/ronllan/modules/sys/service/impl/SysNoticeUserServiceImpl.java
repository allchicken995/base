package com.ronllan.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.sys.dao.SysNoticeUserDao;
import com.ronllan.modules.sys.dto.SysNoticeUserDto;
import com.ronllan.modules.sys.entity.SysNoticeUserEntity;
import com.ronllan.modules.sys.service.SysNoticeUserService;

/**
 * 我的通知
 *
 * @author glq gugameds066@gmail.com
 */
@Service
public class SysNoticeUserServiceImpl extends BaseServiceImpl<SysNoticeUserDao, SysNoticeUserEntity> implements SysNoticeUserService {

	@Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysNoticeUserDto dto) {
		SysNoticeUserEntity entity = ConvertUtils.sourceToTarget(dto, SysNoticeUserEntity.class);
		insert(entity);
    }
	
	@Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysNoticeUserDto dto) {
		SysNoticeUserEntity entity = ConvertUtils.sourceToTarget(dto, SysNoticeUserEntity.class);
		if(entity.getId()!=null) {
			updateById(entity);
		}else {
			SysNoticeUserEntity update = getObject(entity);
			if(update!=null) {
				updateById(entity);
			}
		}
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
    	deleteById(ids);
    }
	
}