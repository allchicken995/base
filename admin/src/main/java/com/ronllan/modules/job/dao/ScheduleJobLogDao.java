package com.ronllan.modules.job.dao;

import com.ronllan.common.dao.BaseDao;

import org.apache.ibatis.annotations.Mapper;

import com.ronllan.modules.job.entity.ScheduleJobLogEntity;

/**
 * 定时任务日志
 *
 * @author glq gugameds066@gmail.com
 */
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {
	
}
