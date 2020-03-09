package io.ac.starter.autoconfigure;

import io.ac.starter.util.BeanUtil;
import io.ac.starter.util.DefaultObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
@Configuration
@EnableConfigurationProperties({
                DomainEventHandlerRedisProperties.class,
                DomainEventHandlerKafkaProperties.class,
                DomainEventHandlerProperties.class
})
@ConditionalOnProperty(
        name = "event.drive",
        havingValue = "enable"
)
@ComponentScan(basePackages = {"io.ac.starter.distributedlock", "io.ac.starter.event"})
@EnableBinding
public class DomainEventHandlerAutoConfiguration {


    @Autowired
    private DomainEventHandlerProperties config;

    private static Logger LOGGER = LoggerFactory.getLogger(DomainEventHandlerAutoConfiguration.class);

    public DomainEventHandlerAutoConfiguration() {
        LOGGER.info("DomainEventHandler registered in the container");
    }

    @Bean
    public DefaultObjectMapper defaultObjectMapper(){

        List<String> strings = config.getScanPackages() == null || config.getScanPackages().isEmpty() ? newArrayList() : config.getScanPackages();
        strings.add("io.ac.iot");
        return new DefaultObjectMapper(strings.toArray(new String[]{}));
    }

    @Bean
    public BeanUtil.ContextLoadedListener beanUtil(){

        return new BeanUtil.ContextLoadedListener();
    }

}
