package com.ronllan.common.service;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基础服务接口，所有Service接口都要继承
 *
 * @author glq gugameds066@gmail.com
 */
public interface BaseService<T> {
    Class<T> currentModelClass();

    /**
     * <p>
     * 插入一条记录（选择字段，策略插入）
     * </p>
     *
     * @param entity 实体对象
     */
    boolean insert(T entity);

    /**
     * <p>
     * 插入（批量）
     * </p>
     *
     * @param entityList 实体对象集合
     */
    boolean insert(Collection<T> entityList);

    /**
     * <p>
     * 根据 ID 选择修改
     * </p>
     *
     * @param entity 实体对象
     */
    boolean updateById(T entity);

    /**
     * <p>
     * 根据ID 批量更新
     * </p>
     *
     * @param entityList 实体对象集合
     */
    boolean updateById(Collection<T> entityList);

    /**
     * <p>
     * 根据 实体对象 查询
     * </p>
     *
     * @param entity 实体对象
     */
    T getObject(T entity);
    
    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     */
    T getObjectById(Serializable id);

    /**
     * <p>
     * 根据 ID 逻辑删除
     * </p>
     *
     * @param id 主键ID
     */
    boolean deleteById(Serializable id);
    
}