package com.ronllan.common.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	@Update("update ${tableName} set ${foreignKey} = null where ${foreignKey}=#{foreignValue} and logical_delete!=0 ")
    int handleForeignKey(T entity);
	/**
     * 逻辑删除
     *
     * @param entity 实体对象
     */
	@ForeignKey
	@Update("update ${tableName} set logical_delete = id where id=#{id} ")
    int delete(T entity);
}
