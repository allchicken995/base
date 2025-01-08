package com.ronllan.modules.security.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ronllan.modules.security.dao.SysUserTokenDao;
import com.ronllan.modules.security.entity.SysUserTokenEntity;
import com.ronllan.modules.security.oauth2.TokenGenerator;
import com.ronllan.modules.security.service.SysUserTokenService;
import com.ronllan.modules.sys.dto.SysUserDto;
import com.ronllan.modules.sys.entity.SysOnlineEntity;

import com.ronllan.common.constant.Constant;
import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.Result;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserTokenServiceImpl extends BaseServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
    /**
     * 12小时后过期
     */
    private final static int EXPIRE = 3600 * 12;

    @Override
    public Result createToken(Long userId) {
        //用户token
        String token;
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        //判断是否生成过token
        SysUserTokenEntity entity = new SysUserTokenEntity();
        entity.setUserId(userId);
        SysUserTokenEntity tokenEntity = getObject(entity);
        if (tokenEntity == null) {
            //生成一个token
            token = TokenGenerator.generateValue();
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateDate(now);
            tokenEntity.setExpireDate(expireTime);
            //保存token
            insert(tokenEntity);
        } else {
            //判断token是否过期
            if (tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()) {
                //token过期，重新生成token
                token = TokenGenerator.generateValue();
            } else {
                token = tokenEntity.getToken();
            }
            tokenEntity.setToken(token);
            tokenEntity.setUpdateDate(now);
            tokenEntity.setExpireDate(expireTime);
            //更新token
            updateById(tokenEntity);
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put(Constant.TOKEN_HEADER, token);
        map.put(Constant.EXPIRE, EXPIRE);
        return new Result().ok(map);
    }

    @Override
    public void logout(Long userId) {
        Date expireDate = DateUtil.offsetMinute(new Date(), -1);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("expireDate", expireDate);
        params.put("userId", userId);
        update("logout",params);
    }

    @Override
    public PageData<SysOnlineEntity> onlinePage(Map<String, Object> params) {
        //查询
        params.put(Constant.EXPIRE_DATE, new Date());
        params.put(Constant.ORDER_FIELD, Constant.UPDATE_DATE);
        return getPage("getOnlineList",params,SysOnlineEntity.class);
    }
}