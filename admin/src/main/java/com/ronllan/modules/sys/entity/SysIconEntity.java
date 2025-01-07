package com.ronllan.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ronllan.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图标管理
 *
 * @author glq gugameds066@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_icon")
public class SysIconEntity extends BaseEntity {
    /**
     * 图标名称
     */
    private String name;
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

}