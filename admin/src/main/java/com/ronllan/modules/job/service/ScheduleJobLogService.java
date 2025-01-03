package com.ronllan.modules.job.service;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;

import java.util.Map;

import com.ronllan.modules.job.dto.ScheduleJobLogDto;
import com.ronllan.modules.job.entity.ScheduleJobLogEntity;

/**
 * 定时任务日志
 *
 * @author glq gugameds066@gmail.com
 */
public interface ScheduleJobLogService extends BaseService<ScheduleJobLogEntity> {

	PageData<ScheduleJobLogDto> page(Map<String, Object> params);

	ScheduleJobLogDto get(Long id);
}
