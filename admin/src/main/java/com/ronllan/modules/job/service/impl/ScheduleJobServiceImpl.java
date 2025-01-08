package com.ronllan.modules.job.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronllan.common.constant.Constant;
import com.ronllan.common.page.PageData;
import com.ronllan.common.service.impl.BaseServiceImpl;
import com.ronllan.common.utils.ConvertUtils;
import com.ronllan.modules.job.dao.ScheduleJobDao;
import com.ronllan.modules.job.dto.ScheduleJobDto;
import com.ronllan.modules.job.entity.ScheduleJobEntity;
import com.ronllan.modules.job.service.ScheduleJobService;
import com.ronllan.modules.job.utils.ScheduleUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJobDao, ScheduleJobEntity> implements ScheduleJobService {
    private final Scheduler scheduler;

    @Override
    public PageData<ScheduleJobDto> page(Map<String, Object> params) {
        return getPage(params,ScheduleJobDto.class);
    }

    @Override
    public ScheduleJobDto get(Long id) {
        ScheduleJobEntity entity = getObjectById(id);
        return ConvertUtils.sourceToTarget(entity, ScheduleJobDto.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ScheduleJobDto dto) {
        ScheduleJobEntity entity = ConvertUtils.sourceToTarget(dto, ScheduleJobEntity.class);
        entity.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        insert(entity);
        ScheduleUtils.createScheduleJob(scheduler, entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJobDto dto) {
        ScheduleJobEntity entity = ConvertUtils.sourceToTarget(dto, ScheduleJobEntity.class);
        ScheduleUtils.updateScheduleJob(scheduler, entity);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        for (Long id : ids) {
            ScheduleUtils.deleteScheduleJob(scheduler, id);
            deleteById(id);
        }
    }

    @Override
    public boolean updateBatch(Long[] ids, int status) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ids", ids);
        params.put("status", status);
        return update("update",params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] ids) {
        for (Long id : ids) {
            ScheduleUtils.run(scheduler, getObjectById(id));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] ids) {
        for (Long id : ids) {
            ScheduleUtils.pauseJob(scheduler, id);
        }
        updateBatch(ids, Constant.ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] ids) {
        for (Long id : ids) {
            ScheduleUtils.resumeJob(scheduler, id);
        }
        updateBatch(ids, Constant.ScheduleStatus.NORMAL.getValue());
    }

}