package com.ronllan.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ronllan.common.dict.ForeignKeyDict;

/**
 * 外键注解
 *
 * @author glq gugameds066@gmail.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ForeignKeyField {
	 /**
     * 关联表
     */
	String[] table() default "";

    /**
     * 处理方式
     */
    String handle() default ForeignKeyDict.NOACTION;
}
