package com.ronllan.modules.job.service;

import com.ronllan.common.page.PageData;
import com.ronllan.common.service.BaseService;

import java.util.Map;

import com.ronllan.modules.job.dto.ScheduleJobDto;
import com.ronllan.modules.job.entity.ScheduleJobEntity;

/**
 * 定时任务
 *
 * @author glq gugameds066@gmail.com
 */
public interface ScheduleJobService extends BaseService<ScheduleJobEntity> {

	PageData<ScheduleJobDto> page(Map<String, Object> params);

	ScheduleJobDto get(Long id);

	/**
	 * 保存定时任务
	 */
	void save(ScheduleJobDto dto);
	
	/**
	 * 更新定时任务
	 */
	void update(ScheduleJobDto dto);
	
	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(Long[] ids);
	
	/**
	 * 批量更新定时任务状态
	 */
	boolean updateBatch(Long[] ids, int status);
	
	/**
	 * 立即执行
	 */
	void run(Long[] ids);
	
	/**
	 * 暂停运行
	 */
	void pause(Long[] ids);
	
	/**
	 * 恢复运行
	 */
	void resume(Long[] ids);
}
