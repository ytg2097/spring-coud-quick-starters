package io.ac.starter.autoconfigure;

import io.ac.starter.idempotency.IdempotencyOperationInterceptor;
import io.ac.starter.idempotency.OpTokenRepository;
import io.ac.starter.redis.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
@EnableConfigurationProperties(IdempotencyProperties.class)
@ConditionalOnClass(RedisClient.class)
@ConditionalOnProperty
        (
                prefix = "io.ac.iot.idempotency",
                value = "enabled",
                matchIfMissing = true
        )
public class IdempotencyAutoConfiguration {

    @Autowired
    private IdempotencyProperties idempotencyProperties;

    private static Logger LOGGER = LoggerFactory.getLogger(IdempotencyAutoConfiguration.class);

    public IdempotencyAutoConfiguration() {
        LOGGER.info("IdempotencyOperationInterceptor registered in the container");
    }

    @Bean
    public IdempotencyOperationInterceptor idempotencyOperationInterceptor() {
        return new IdempotencyOperationInterceptor();
    }

    /**
     *
     * @return
     */
    @Bean
    public OpTokenRepository opTokenRepository(){
        return new OpTokenRepository(idempotencyProperties);
    }
}

