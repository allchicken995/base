package com.ronllan.modules.security.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ronllan.common.entity.BaseEntity;

import lombok.Data;

/**
 * 系统用户Token
 */
@Data
@TableName("sys_user_token")
public class SysUserTokenEntity extends BaseEntity implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户token
     */
    private String token;
    /**
     * 过期时间
     */
    private Date expireDate;
    /**
     * 更新时间
     */
    private Date updateDate;

}