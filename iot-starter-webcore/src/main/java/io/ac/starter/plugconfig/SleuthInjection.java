package io.ac.starter.plugconfig;

import io.ac.starter.sleuth.SleuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-10
 **/
public class SleuthInjection extends WebMvcConfigurationSupport {

    @Autowired
    private SleuthInterceptor sleuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(sleuthInterceptor);
    }
}
