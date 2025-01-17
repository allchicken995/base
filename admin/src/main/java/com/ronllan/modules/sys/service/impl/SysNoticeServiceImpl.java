package com.ronllan.modules.sys.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dao.SysNoticeDao;
import com.ronllan.modules.sys.dto.SysNoticeDto;
import com.ronllan.modules.sys.dto.SysNoticeUserDto;
import com.ronllan.modules.sys.dto.SysUserDto;
import com.ronllan.modules.sys.entity.SysNoticeEntity;
import com.ronllan.modules.sys.enums.NoticeReadStatusEnum;
import com.ronllan.modules.sys.enums.NoticeStatusEnum;
import com.ronllan.modules.sys.service.SysNoticeService;
import com.ronllan.modules.sys.service.SysNoticeUserService;
import com.ronllan.modules.sys.service.SysUserService;

import lombok.AllArgsConstructor;

/**
 * 通知管理
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@Service
public class SysNoticeServiceImpl extends BaseServiceImpl<SysNoticeDao, SysNoticeEntity> implements SysNoticeService {
    private final SysNoticeUserService sysNoticeUserServiceImpl;
    private final SysUserService sysUserServiceImpl;

	@Override
    public PageData<SysNoticeDto> page(Map<String, Object> params) {
        return getPage(params,SysNoticeDto.class);
    }
	
    @Override
    public PageData<SysNoticeDto> getMyNoticePage(Map<String, Object> params) {
        return getPage("getMyNoticeList",params,SysNoticeDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysNoticeDto dto) {
        SysNoticeEntity entity = ConvertUtils.sourceToTarget(dto, SysNoticeEntity.class);
        //更新发送者信息
        if (dto.getStatus() == NoticeStatusEnum.SEND.value()) {
            entity.setSenderName(SecurityUser.getUser().getRealName());
            entity.setSenderDate(new Date());
        }
        insert(entity);
        if (dto.getStatus() == NoticeStatusEnum.SEND.value()) {
	        List<SysUserDto> dtoList = sysUserServiceImpl.list(new HashMap<String, Object>());
	        for(SysUserDto sysUserDto : dtoList) {
	        	SysNoticeUserDto sysNoticeUserDto = new SysNoticeUserDto();
	        	sysNoticeUserDto.setReceiverId(sysUserDto.getId());
	        	sysNoticeUserDto.setNoticeId(entity.getId());
	        	sysNoticeUserDto.setReadStatus(NoticeReadStatusEnum.UNREAD.value());
	        	sysNoticeUserServiceImpl.save(sysNoticeUserDto);
	        }
        }
    }
    
    @Override
    public SysNoticeDto get(Long id) {
    	SysNoticeEntity entity = getObjectById(id);
        return ConvertUtils.sourceToTarget(entity, SysNoticeDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysNoticeDto dto) {
        SysNoticeEntity entity = ConvertUtils.sourceToTarget(dto, SysNoticeEntity.class);
        //更新发送者信息
        if (dto.getStatus() == NoticeStatusEnum.SEND.value()) {
            entity.setSenderName(SecurityUser.getUser().getRealName());
            entity.setSenderDate(new Date());
        }
        updateById(entity);
        if (dto.getStatus() == NoticeStatusEnum.SEND.value()) {
	        List<SysUserDto> dtoList = sysUserServiceImpl.list(new HashMap<String, Object>());
	        for(SysUserDto sysUserDto : dtoList) {
	        	SysNoticeUserDto sysNoticeUserDto = new SysNoticeUserDto();
	        	sysNoticeUserDto.setReceiverId(sysUserDto.getId());
	        	sysNoticeUserDto.setNoticeId(entity.getId());
	        	sysNoticeUserDto.setReadStatus(NoticeReadStatusEnum.UNREAD.value());
	        	sysNoticeUserServiceImpl.save(sysNoticeUserDto);
	        }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long[] ids) {
    	deleteById(ids);
    }

}