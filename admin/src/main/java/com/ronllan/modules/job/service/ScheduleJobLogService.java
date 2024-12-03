package com.ronllan.modules.job.service;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;

import java.util.Map;

import com.ronllan.modules.job.dto.ScheduleJobLogDTO;
import com.ronllan.modules.job.entity.ScheduleJobLogEntity;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobLogService extends BaseService<ScheduleJobLogEntity> {

	PageData<ScheduleJobLogDTO> page(Map<String, Object> params);

	ScheduleJobLogDTO get(Long id);
}
