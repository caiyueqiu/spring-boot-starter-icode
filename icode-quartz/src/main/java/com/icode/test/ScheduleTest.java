package com.icode.test;

import com.icode.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/4/28 9:00
 */
public class ScheduleTest {
    public static void main(String[] args) throws Exception {
        // 调度器（Schedule），从工厂实例中获取
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 任务实例（JobDetail），参数1：任务的名称，唯一实例，参数2：任务组的名称
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("jobName", "jobGroup").build();
        // 触发器（Trigger），参数1：触发器的名称，唯一实例，参数2：触发器组的名称
        Trigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("triggerName", "triggerGroup").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(5)).build();
        // 让调度器关联任务和触发器，保证按照触发器定义的条件执行任务
        scheduler.scheduleJob(jobDetail, simpleTrigger);
        // 启动
        scheduler.start();
    }
}
