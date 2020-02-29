package io.ac.starter.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
@ConfigurationProperties(prefix = "spring.redis")
@Data
class DomainEventHandlerRedisProperties {


    private String host;

    private int port;

    private String password;

    private int timeout;

}

@ConfigurationProperties(prefix = "spring.cloud.stream.kafka")
@Data
class DomainEventHandlerKafkaProperties {


    private List<String> bootstrapServers;

}

@ConfigurationProperties(prefix = "event.types")
@Data
class DomainEventHandlerProperties {

    private List<String> scanPackages;

    public List<String> getScanPackages() {
        return scanPackages;
    }

}

