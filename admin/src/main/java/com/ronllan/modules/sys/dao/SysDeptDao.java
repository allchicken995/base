package com.ronllan.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.sys.entity.SysDeptEntity;

/**
 * 部门管理
 * 
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface SysDeptDao extends BaseDao<SysDeptEntity> {

    List<SysDeptEntity> getObjectList(Map<String, Object> params);

}