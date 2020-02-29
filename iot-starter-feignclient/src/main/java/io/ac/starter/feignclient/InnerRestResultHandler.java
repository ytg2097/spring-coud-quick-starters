package io.ac.starter.feignclient;

import common.JsonUtil;
import common.rest.RestResult;
import feign.FeignException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@Aspect
@Configuration
public class InnerRestResultHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(InnerRestResultHandler.class);

    @Autowired
    private InnerHooker innerHooker;

    /**
     * 慢方法时长3s
     */
    private static final long VALUE_SLOW_TIME = 3000;

    @Pointcut("execution(* io.ac.starter.*.remote..*Manager.*(..))")
    public void innerServiceInvocation() {
    }

    @Around("innerServiceInvocation()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;

        Class targetClass = pjp.getTarget().getClass();
        String targetMethod = pjp.getSignature().getName();

        try {
            String sname = pjp.getSignature().getName();
            LOGGER.info("remote method: " + sname + "  args:" + Arrays.toString(pjp.getArgs()));
            long before = System.currentTimeMillis();
            result = pjp.proceed();
            long after = System.currentTimeMillis();
            long cost = after - before;
            LOGGER.info(sname + " cost:" + cost + " ms");
            // 慢方法记录
            if (cost >= VALUE_SLOW_TIME ) {
                LOGGER.info("slowMethod:{},cost:{}", targetClass + ":" + targetMethod, cost);
            }
        } catch (FeignException e) {
            // 记录feign异常
            LOGGER.info("feignExceptionMethod:{},status:{},message:{}", targetClass + ":" + targetMethod, e.status(), e.getMessage());
            innerHooker.doFeignException(e);
        }

        if (result instanceof RestResult) {
            RestResult restResult = (RestResult) result;
            innerHooker.doInnerException(restResult, targetClass, targetMethod, JsonUtil.toJson(pjp.getArgs()));
        }

        return result;
    }

}

