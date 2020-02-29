package io.ac.starter.autoconfigure;

import io.ac.starter.logging.LogbackMdcTaskDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@Configuration
@EnableAsync
@EnableScheduling
public class SchedulingAutoConfiguration implements SchedulingConfigurer {

    private static Logger LOGGER = LoggerFactory.getLogger(SchedulingAutoConfiguration.class);

    public SchedulingAutoConfiguration() {
        LOGGER.info("SchedulingTaskExecutor registered in the container");
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(newScheduledThreadPool(10));
    }

    @Bean(destroyMethod = "shutdown")
    @Primary
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(10);
        executor.setTaskDecorator(new LogbackMdcTaskDecorator());
        executor.initialize();
        return executor;
    }

}
