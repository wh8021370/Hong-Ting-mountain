package com.paradise.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author wh
 * @date 2018/9/18
 */
public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        //创建jobDetail实例，与HelloJob绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("helloJob").build();
        //创建trigger实例，定义该job定时执行，每两秒执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("helloJob").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(20).repeatForever()).build();
        //创建scheduler实例，执行定时任务
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);



    }
}
