package io.ac.starter.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangtg
 * @create: 2019-11-12
 **/
public class BeanUtil {

    private static ApplicationContext context;

    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz){
        return context.getBean(beanName,clazz);
    }

    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }

    public static <T> T getBean(Class<T> clazz, Object... objects){
        return context.getBean(clazz, objects);
    }


    @Component
    @Lazy(false)
    @Order(-2147483647)
    public static class ContextLoadedListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

            ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
            BeanUtil.context = applicationContext;
        }
    }
}
