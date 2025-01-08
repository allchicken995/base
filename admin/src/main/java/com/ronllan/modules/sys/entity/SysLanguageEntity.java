package com.ronllan.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ronllan.common.entity.BaseEntity;

import lombok.Data;

/**
 * 国际化
 *
 * @author glq gugameds066@gmail.com
 */
@Data
@TableName("sys_language")
public class SysLanguageEntity extends BaseEntity {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表主键
     */
    private Long tableId;
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 字段值
     */
    private String fieldValue;
    /**
     * 语言
     */
    private String language;

}