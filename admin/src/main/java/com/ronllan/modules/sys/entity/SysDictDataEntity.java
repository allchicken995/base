package com.ronllan.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ronllan.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据字典
 *
 * @author glq gugameds066@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict_data")
public class SysDictDataEntity extends BaseEntity {
    /**
     * 字典类型ID
     */
    private Long dictTypeId;
    /**
     * 字典标签
     */
    private String dictLabel;
    /**
     * 字典值
     */
    private String dictValue;
    /**
     * 备注
     */
    private String remark;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;
}