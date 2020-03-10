package io.ac.starter.plugconfig;

import io.ac.starter.autoconfigure.WebCoreProperties;
import io.ac.starter.idempotency.IdempotencyOperationInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-10
 **/
public class IdempotencyInjection extends WebMvcConfigurationSupport {

    private WebCoreProperties.Idempotency idempotency;
    private IdempotencyOperationInterceptor idempotencyOperationInterceptor;

    public IdempotencyInjection(WebCoreProperties.Idempotency idempotency, IdempotencyOperationInterceptor idempotencyOperationInterceptor) {
        this.idempotency = idempotency;
        this.idempotencyOperationInterceptor = idempotencyOperationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(idempotencyOperationInterceptor)
                .addPathPatterns(idempotency.getPath());
    }
}
