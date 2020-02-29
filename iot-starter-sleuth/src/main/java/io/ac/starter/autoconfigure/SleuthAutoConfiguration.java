package io.ac.starter.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.sleuth.annotation.SleuthAnnotationAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@Configuration
@EnableConfigurationProperties(SleuthProperties.class)
@ConditionalOnClass(SleuthAnnotationAutoConfiguration.class)
@ConditionalOnProperty
        (
                prefix = "spring.application",
                value = "enabled",
                matchIfMissing = true
        )
@ComponentScan(basePackages = {"io.ac.starter.sleuth"})
public class SleuthAutoConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(SleuthAutoConfiguration.class);

    public SleuthAutoConfiguration() {
        LOGGER.info("SleuthInterceptor registered in the container");
    }
}
