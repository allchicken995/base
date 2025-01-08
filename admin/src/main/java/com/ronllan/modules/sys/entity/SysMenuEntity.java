package com.ronllan.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ronllan.common.annotation.ForeignKeyField;
import com.ronllan.common.constant.Constant;
import com.ronllan.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单管理
 *
 * @author glq gugameds066@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
public class SysMenuEntity extends BaseEntity {
	/**
     * 外键
     */
	@TableField(exist = false)
	@ForeignKeyField(handle={
    		"com.ronllan.modules.sys.entity.SysRoleMenuEntity.roleId="+Constant.CASCADE
    	   ,"com.ronllan.modules.sys.entity.SysRoleUserEntity.roleId="+Constant.CASCADE
    	   ,"com.ronllan.modules.sys.entity.SysLanguageEntity.tableId="+Constant.CASCADE
    })
	private Long fk;
    /**
     * 父菜单ID，一级菜单为0
     */
    private Long pid;
    /**
     * 菜单名称
     */
    @TableField(exist = false)
    private String name;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 授权(多个用逗号分隔，如：sys:user:list,sys:user:save)
     */
    private String permissions;
    /**
     * 类型   0：菜单   1：按钮
     */
    private Integer menuType;
    /**
     * 打开方式   0：内部   1：外部
     */
    private Integer openStyle;
    /**
     * 正常图标
     */
    private String normalIcon;
    /**
     * 选中图标
     */
    private String selectedIcon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 上级菜单名称
     */
    @TableField(exist = false)
    private String parentName;

}