package com.agileboot.infrastructure.schedule;

import cn.hutool.core.date.DateUtil;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 如果想开启定时任务   请不要注释@Component注解
 * @author valarchie
 */
//@Component
@Slf4j
public class ScheduleJobManager {

    /**
     * fixedRate：固定速率执行。每60秒执行一次。
     */
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTimeWithFixedRate() {
        log.info("Current Thread : {}, Fixed Rate Task : The time is now {}",
            Thread.currentThread().getName(), DateUtil.formatTime(new Date()));
    }

    /**
     * fixedDelay：固定延迟执行。距离上一次调用成功后30秒才执。
     */
    @Scheduled(fixedDelay = 30000)
    public void reportCurrentTimeWithFixedDelay() {
        try {
            TimeUnit.SECONDS.sleep(60);
            log.info("Current Thread : {}, Fixed Delay Task : The time is now {}",
                Thread.currentThread().getName(), DateUtil.formatTime(new Date()));
        } catch (InterruptedException e) {
            log.error("计划任务执行失败", e);
        }
    }

    /**
     * initialDelay:初始延迟。任务的第一次执行将延迟30秒，然后将以60秒的固定间隔执行。
     */
    @Scheduled(initialDelay = 30000, fixedRate = 60000)
    public void reportCurrentTimeWithInitialDelay() {
        log.info("Current Thread : {}, Fixed Rate Task with Initial Delay : The time is now {}",
            Thread.currentThread().getName(), DateUtil.formatTime(new Date()));
    }

    /**
     * cron：使用Cron表达式。　每分钟的1，2秒运行
     * <a href="https://cron.qqe2.com/">https://cron.qqe2.com/</a>
     * cron表达式 在线解析
     */
    @Scheduled(cron = "1-2 * * * * ? ")
    public void reportCurrentTimeWithCronExpression() {
        log.info("Current Thread : {}, Cron Expression: The time is now {}",
            Thread.currentThread().getName(), DateUtil.formatTime(new Date()));
    }


}
