package com.ronllan.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.sys.entity.SysDictDataEntity;

/**
 * 字典数据
 *
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface SysDictDataDao extends BaseDao<SysDictDataEntity> {

	/**
     * 字典数据列表
     */
    List<SysDictDataEntity> getList(Map<String, Object> params);
    
}
