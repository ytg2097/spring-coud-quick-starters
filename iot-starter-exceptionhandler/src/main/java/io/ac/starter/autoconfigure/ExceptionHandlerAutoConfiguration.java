package io.ac.starter.autoconfigure;

import io.ac.starter.exceptionhandler.RestExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@Configuration
@EnableConfigurationProperties(ExceptionHandlerProperties.class)
@ConditionalOnProperty
        (
                prefix = "spring.application",
                value = "enabled",
                matchIfMissing = true
        )

public class ExceptionHandlerAutoConfiguration {

    @Autowired
    private ExceptionHandlerProperties exceptionHandlerProperties;

    private static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAutoConfiguration.class);

    public ExceptionHandlerAutoConfiguration() {
        LOGGER.info("ExceptionHandler config registered in the container");
    }

    @Bean
    public RestExceptionHandler restExceptionHandler(){
        return new RestExceptionHandler();
    }
}
