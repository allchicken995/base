package com.ronllan.common.dao;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ronllan.common.annotation.ForeignKey;

/**
 * 基础Dao
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
public interface BaseDao<T> extends BaseMapper<T> {
	/**
     * 外键处理逻辑
     *
     * @param entity 实体对象
     */
    int handleForeignKey(T entity);
	/**
     * 根据 ID 逻辑删除
     *
     * @param id 主键ID
     */
	@ForeignKey
    int delete(Serializable id);
}
