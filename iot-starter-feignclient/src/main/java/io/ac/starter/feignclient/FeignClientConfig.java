package io.ac.starter.feignclient;

import common.WebConst;
import feign.RequestInterceptor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@Configuration
public class FeignClientConfig {

    @Autowired
    private FeignParamValueProvider feignParamValueProvider;
    /**
     * 主要用于跨服务调用时传入某些参数，便于服务内部使用。
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String tid = getTid();
            if (tid != null) {
                requestTemplate.header(WebConst.REQUEST_HEADER_TID, tid);
            }
            // 加入操作用户的信息，便于内部服务获取审计信息
            String operatorId = getOperatorId();
            if (operatorId != null) {
                requestTemplate.header(WebConst.REQUEST_HEADER_OPERATOR_ID, operatorId);
            }
            // 加入操作Token的信息，便于内部服务进行幂等性控制
            requestTemplate.query(WebConst.REQUEST_HEADER_OPERATOR_TOKEN, RandomStringUtils.randomAlphabetic(8));
        };
    }

    private String getTid() {
        return feignParamValueProvider == null ? null : feignParamValueProvider.findTid();
    }

    private String getOperatorId() {
        return feignParamValueProvider == null ? null : feignParamValueProvider.findOperatorId();
    }
}

