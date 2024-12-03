package com.ronllan.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 国际化
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_language")
public class SysLanguageEntity implements Serializable {
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