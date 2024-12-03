package com.ronllan.modules.security.dao;

import com.ronllan.common.dao.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ronllan.modules.security.entity.SysUserTokenEntity;
import com.ronllan.modules.sys.entity.SysOnlineEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统用户Token
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {

    SysUserTokenEntity getByToken(String token);

    SysUserTokenEntity getByUserId(Long userId);

    void logout(@Param("userId") Long userId, @Param("expireDate") Date expireDate);

    /**
     * 获取在线用户列表
     */
    List<SysOnlineEntity> getOnlineList(Map<String, Object> params);
}
