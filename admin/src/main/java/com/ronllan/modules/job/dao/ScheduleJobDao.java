package com.ronllan.modules.job.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.common.dao.BaseDao;
import com.ronllan.modules.job.entity.ScheduleJobEntity;

/**
 * 定时任务
 *
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {
	
}
