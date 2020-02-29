package io.ac.starter.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@Configuration
@EnableConfigurationProperties(FeignClientProperties.class)
@ConditionalOnClass(EnableFeignClients.class)
@ConditionalOnProperty
        (
                prefix = "io.ac.iot.feignclient",
                value = "enabled",
                matchIfMissing = true
        )
@ComponentScan(basePackages = {"io.ac.starter.feignclient"})
public class FeignClientAutoConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(FeignClientAutoConfiguration.class);

    public FeignClientAutoConfiguration() {
        LOGGER.info("FeignClient registered in the container");
    }
}
