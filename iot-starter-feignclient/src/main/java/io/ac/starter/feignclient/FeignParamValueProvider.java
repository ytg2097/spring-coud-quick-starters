package io.ac.starter.feignclient;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
public interface FeignParamValueProvider {

    String DEFAULT_OPERATOR_ID = "DEFAULT_OPERATOR";
    String DEFAULT_TENANT_ID = "DEFAULT_TENANT";
    /**
     * 返回平台租户id
     * @return
     */
    String findTid();

    /**
     * 返回本次请求发起者id
     * @return
     */
    String findOperatorId();
}
