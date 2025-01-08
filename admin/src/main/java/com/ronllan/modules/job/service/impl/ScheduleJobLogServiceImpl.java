package com.ronllan.modules.job.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.job.dao.ScheduleJobLogDao;
import com.ronllan.modules.job.dto.ScheduleJobLogDto;
import com.ronllan.modules.job.entity.ScheduleJobLogEntity;
import com.ronllan.modules.job.service.ScheduleJobLogService;

@Service
public class ScheduleJobLogServiceImpl extends BaseServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

	@Override
	public PageData<ScheduleJobLogDto> page(Map<String, Object> params) {
		return getPage(params,ScheduleJobLogDto.class);
	}

	@Override
	public ScheduleJobLogDto get(Long id) {
		ScheduleJobLogEntity entity = getObjectById(id);
		return ConvertUtils.sourceToTarget(entity, ScheduleJobLogDto.class);
	}

}