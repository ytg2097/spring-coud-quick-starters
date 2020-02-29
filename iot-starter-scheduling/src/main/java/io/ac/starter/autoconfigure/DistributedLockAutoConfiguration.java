package io.ac.starter.autoconfigure;

import io.ac.starter.distributedlock.DistributedLockExecutor;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@Configuration
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class DistributedLockAutoConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(DistributedLockAutoConfiguration.class);

    public DistributedLockAutoConfiguration() {
        LOGGER.info("DistributedLock registered in the container");
    }
    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource);
    }


    @Bean
    public DistributedLockExecutor distributedLockExecutor(LockProvider lockProvider) {
        return new DistributedLockExecutor(lockProvider);
    }
}
