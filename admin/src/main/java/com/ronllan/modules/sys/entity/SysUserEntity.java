package com.ronllan.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ronllan.common.annotation.ForeignKeyField;
import com.ronllan.common.constant.Constant;
import com.ronllan.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户
 *
 * @author glq gugameds066@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class SysUserEntity extends BaseEntity {
	/**
     * 外键
     */
	@TableField(exist = false)
    @ForeignKeyField(handle={
    		"com.ronllan.modules.security.entity.SysUserTokenEntity.userId="+Constant.CASCADE
    	   ,"com.ronllan.modules.sys.entity.SysUserPostEntity.userId="+Constant.CASCADE
    	   ,"com.ronllan.modules.sys.entity.SysRoleUserEntity.userId="+Constant.CASCADE
    	   ,"com.ronllan.modules.sys.entity.SysNoticeUserEntity.receiverId="+Constant.CASCADE
    })
    private Long fk;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 头像
     */
    private String headUrl;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 超级管理员   0：否   1：是
     */
    private Integer superAdmin;
    /**
     * 状态  0：停用   1：正常
     */
    private Integer status;
    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;

}