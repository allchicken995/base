package com.ronllan.modules.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.user.UserDetail;
import com.ronllan.modules.security.user.SecurityUser;
import com.ronllan.modules.sys.dao.SysNoticeDao;
import com.ronllan.modules.sys.dto.SysNoticeDto;
import com.ronllan.modules.sys.dto.SysUserDto;
import com.ronllan.modules.sys.entity.SysNoticeEntity;
import com.ronllan.modules.sys.entity.SysNoticeUserEntity;
import com.ronllan.modules.sys.enums.SuperAdminEnum;
import com.ronllan.modules.sys.service.SysDeptService;
import com.ronllan.modules.sys.service.SysNoticeService;
import com.ronllan.modules.sys.service.SysNoticeUserService;
import com.ronllan.modules.sys.service.SysUserService;
import com.ronllan.websocket.data.MessageData;
import lombok.AllArgsConstructor;

/**
 * 通知管理
 *
 * @author glq gugameds066@gmail.com
 */
@AllArgsConstructor
@Service
public class SysNoticeServiceImpl extends BaseServiceImpl<SysNoticeDao, SysNoticeEntity> implements SysNoticeService {
//    private final SysNoticeUserService sysNoticeUserService;
//    private final SysUserService sysUserService;
//    private final WebSocketServer webSocketServer;
	private final SysDeptService sysDeptServiceImpl;

	@Override
    public PageData<SysNoticeDto> page(Map<String, Object> params) {
        //普通管理员，只能查询所属部门及子部门的数据
        UserDetail user = SecurityUser.getUser();
        if (user.getSuperAdmin() == SuperAdminEnum.NO.value()) {
            params.put("deptIdList", sysDeptServiceImpl.getSubDeptIdList(user.getDeptId()));
        }
        return getPage(params,SysNoticeDto.class);
    }
	
//    @Override
//    public PageData<SysNoticeDTO> getNoticeUserPage(Map<String, Object> params) {
//        //分页
//        IPage<SysNoticeEntity> page = getPage(params, null, false);
//
//        //查询
//        List<SysNoticeEntity> list = baseDao.getNoticeUserList(params);
//
//        return getPageData(list, page.getTotal(), SysNoticeDTO.class);
//    }
//
//    @Override
//    public PageData<SysNoticeDTO> getMyNoticePage(Map<String, Object> params) {
//        //分页
//        IPage<SysNoticeEntity> page = getPage(params, null, false);
//
//        //查询
//        params.put("receiverId", SecurityUser.getUserId());
//        List<SysNoticeEntity> list = baseDao.getMyNoticeList(params);
//
//        return getPageData(list, page.getTotal(), SysNoticeDTO.class);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void save(SysNoticeDTO dto) {
//        SysNoticeEntity entity = ConvertUtils.sourceToTarget(dto, SysNoticeEntity.class);
//
//        //更新发送者信息
//        if (dto.getStatus() == NoticeStatusEnum.SEND.value()) {
//            entity.setSenderName(SecurityUser.getUser().getRealName());
//            entity.setSenderDate(new Date());
//        }
//
//        baseDao.insert(entity);
//
//        //发送通知
//        dto.setId(entity.getId());
//        sendNotice(dto);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void update(SysNoticeDTO dto) {
//        SysNoticeEntity entity = ConvertUtils.sourceToTarget(dto, SysNoticeEntity.class);
//
//        //更新发送者信息
//        if (dto.getStatus() == NoticeStatusEnum.SEND.value()) {
//            entity.setSenderName(SecurityUser.getUser().getRealName());
//            entity.setSenderDate(new Date());
//        }
//
//        this.updateById(entity);
//
//        //发送通知
//        sendNotice(dto);
//    }
//
//    /**
//     * 发送通知
//     */
//    public void sendNotice(SysNoticeDTO notice) {
//        //如果是草稿，在不发送通知
//        if (notice.getStatus() == NoticeStatusEnum.DRAFT.value()) {
//            return;
//        }
//
//        //全部用户
//        if (notice.getReceiverType() == ReceiverTypeEnum.ALL.value()) {
//            //发送给全部用户
//            sendAllUser(notice);
//
//            //通过WebSocket，提示全部用户，有新通知
//            MessageData<String> message = new MessageData<String>().msg(notice.getTitle());
//            webSocketServer.sendMessageAll(message);
//
//        } else {  //选中用户
//            List<Long> userIdList = sysUserService.getUserIdListByDeptId(notice.getReceiverTypeList());
//            if (userIdList.size() == 0) {
//                return;
//            }
//
//            //发送给选中用户
//            sendUser(notice, userIdList);
//
//            //通过WebSocket，提示选中用户，有新通知
//            MessageData<String> message = new MessageData<String>().msg(notice.getTitle());
//            webSocketServer.sendMessage(userIdList, message);
//        }
//    }
//
//    /**
//     * 发送给全部用户
//     */
//    public void sendAllUser(SysNoticeDTO notice) {
//        SysNoticeUserEntity noticeUser = new SysNoticeUserEntity()
//                .setNoticeId(notice.getId())
//                .setReadStatus(NoticeReadStatusEnum.UNREAD.value());
//        sysNoticeUserService.insertAllUser(noticeUser);
//    }
//
//    /**
//     * 发送给选中用户
//     */
//    public void sendUser(SysNoticeDTO notice, List<Long> userIdList) {
//        userIdList.forEach(userId -> {
//            SysNoticeUserEntity noticeUser = new SysNoticeUserEntity()
//                    .setNoticeId(notice.getId())
//                    .setReceiverId(userId)
//                    .setReadStatus(NoticeReadStatusEnum.UNREAD.value());
//
//            sysNoticeUserService.save(noticeUser);
//        });
//    }


}