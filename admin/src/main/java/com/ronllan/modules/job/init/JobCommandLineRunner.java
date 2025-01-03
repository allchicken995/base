package com.ronllan.modules.job.init;

import jakarta.annotation.Resource;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ronllan.modules.job.dao.ScheduleJobDao;
import com.ronllan.modules.job.entity.ScheduleJobEntity;
import com.ronllan.modules.job.utils.ScheduleUtils;

import java.util.List;

/**
 * 初始化定时任务数据
 *
 * @author glq gugameds066@gmail.com
 */
@Component
public class JobCommandLineRunner implements CommandLineRunner {
    @Resource
    private Scheduler scheduler;
    @Resource
    private ScheduleJobDao scheduleJobDao;

    @Override
    public void run(String... args) {
        List<ScheduleJobEntity> scheduleJobList = scheduleJobDao.selectList(null);
        for (ScheduleJobEntity scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }
}