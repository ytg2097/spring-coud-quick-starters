package io.ac.starter.autoconfigure;

import io.ac.starter.operatorid.OperatorIdHolder;
import io.ac.starter.tenant.TenantIdHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@EnableConfigurationProperties({IdHolderProperties.class})
@ConditionalOnProperty(
        prefix = "spring.application",
        value = {"enabled"},
        matchIfMissing = true
)
public class IdHolderAutoConfiguration {
    private static Logger LOGGER = LoggerFactory.getLogger(IdHolderAutoConfiguration.class);

    public IdHolderAutoConfiguration() {
        LOGGER.info("IdHolder registered in the container");
    }
    @Bean
    public TenantIdHolder tidHolder() {
        return new TenantIdHolder();
    }

    @Bean
    public OperatorIdHolder operatorIdHolder() {
        return new OperatorIdHolder();
    }

}
