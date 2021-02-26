package cn.jwis.qualityworkflow.config;

/**
 * iqc-inspection-service
 * 2019/12/26 16:31
 *
 * @author
 * @description 配置定时任务
 **/

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(getThreadPoolTaskScheduler());
    }

    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
        ThreadPoolTaskScheduler poolTaskScheduler = new ThreadPoolTaskScheduler();
        poolTaskScheduler.setPoolSize(10);
        poolTaskScheduler.setThreadNamePrefix("taskThread-");
        poolTaskScheduler.setAwaitTerminationSeconds(60);
        poolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        poolTaskScheduler.afterPropertiesSet();
        return poolTaskScheduler;
    }
}
