package com.ronllan.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ronllan.common.entity.BaseEntity;

import lombok.Data;

/**
 * 用户岗位关系
 *
 * @author glq gugameds066@gmail.com
 */
@Data
@TableName("sys_user_post")
public class SysUserPostEntity extends BaseEntity{
    /**
     * 岗位ID
     */
    private Long postId;
    /**
     * 用户ID
     */
    private Long userId;
}