package io.ac.starter.autoconfigure;

import io.ac.starter.redis.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@Configuration
@EnableConfigurationProperties(RedisClientProperties.class)
@ConditionalOnClass(StringRedisTemplate.class)
public class RedisClientAutoConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(RedisClientAutoConfiguration.class);

    public RedisClientAutoConfiguration() {
        LOGGER.info("RedisClient registered in the container");
    }
    @Bean
    public RedisClient redisClient() {
        return new RedisClient();
    }
}
