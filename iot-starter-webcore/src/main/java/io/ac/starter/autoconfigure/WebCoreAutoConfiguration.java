package io.ac.starter.autoconfigure;

import io.ac.starter.corsconfig.CorsConfig;
import io.ac.starter.exceptionhandler.RestExceptionHandler;
import io.ac.starter.idempotency.IdempotencyOperationInterceptor;
import io.ac.starter.plugconfig.IdempotencyInjection;
import io.ac.starter.plugconfig.SleuthInjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@Configuration
@EnableConfigurationProperties({WebCoreProperties.class})
public class WebCoreAutoConfiguration {

    @Autowired
    private WebCoreProperties webCoreProperties;
    private static Logger LOGGER = LoggerFactory.getLogger(WebCoreAutoConfiguration.class);

    public WebCoreAutoConfiguration() {
        LOGGER.info("Webcore config registered in the container");
    }

    @Bean
    @ConditionalOnProperty
            (
                    prefix = "spring.application",
                    value = "enabled",
                    matchIfMissing = true
            )

    public RestExceptionHandler restExceptionHandler(){
        LOGGER.info("RestExceptionHandler config registered in the container");
        return new RestExceptionHandler();
    }

    @Bean
    @ConditionalOnProperty
            (
                    prefix = "ac.web.cors",
                    value = "enable",
                    matchIfMissing = true
            )
    public CorsFilter corsFilter(){

        return new CorsConfig(webCoreProperties.getCors()).corsFilter();
    }

    @Bean
    @ConditionalOnBean(IdempotencyAutoConfiguration.class)
    public IdempotencyInjection idempotencyInjection(IdempotencyOperationInterceptor idempotencyOperationInterceptor){

        LOGGER.info("Idempotency config registered in the container");
        return new IdempotencyInjection(webCoreProperties.getIdempotency(), idempotencyOperationInterceptor);
    }

    @Bean
    @ConditionalOnBean(SleuthAutoConfiguration.class)
    public SleuthInjection sleuthInjection(){

        LOGGER.info("Sleuth config registered in the container");
        return new SleuthInjection();
    }
}
