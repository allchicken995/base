package com.ronllan.modules.sys.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ronllan.common.annotation.ForeignKeyField;
import com.ronllan.common.dict.ForeignKeyDict;
import com.ronllan.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门管理
 *
 * @author glq gugameds066@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dept")
public class SysDeptEntity extends BaseEntity {
	/**
     * 外键
     */
	@TableField(exist = false)
	@ForeignKeyField(handle={
    		"com.ronllan.modules.sys.entity.SysUserEntity.deptId="+ForeignKeyDict.SETNULL
    	   ,"com.ronllan.modules.sys.entity.SysUserEntity.deptId="+ForeignKeyDict.SETNULL
    	   ,"com.ronllan.modules.sys.entity.SysRoleEntity.deptId="+ForeignKeyDict.SETNULL
    	   ,"com.ronllan.modules.sys.entity.SysRoleDataScopeEntity.deptId="+ForeignKeyDict.SETNULL
    })
	private Long fk;
	/**
     * 上级ID
     */
    private Long pid;
    /**
     * 所有上级ID，用逗号分开
     */
    private String pids;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 负责人ID
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private Long leaderId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updater;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
    /**
     * 上级部门名称
     */
    @TableField(exist = false)
    private String parentName;

}