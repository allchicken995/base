package com.ronllan.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.sys.entity.SysDictTypeEntity;

/**
 * 字典类型
 *
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface SysDictTypeDao extends BaseDao<SysDictTypeEntity> {

    /**
     * 字典类型列表
     */
    List<SysDictTypeEntity> getList(Map<String, Object> params);
    /**
     * 根据ID获取字典类型
     */
    SysDictTypeEntity getObjectById(Long id);
    
}
