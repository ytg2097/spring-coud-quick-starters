package io.ac.starter.feignclient;

import common.rest.RestResult;
import common.rest.RestStatus;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
public interface InnerHooker {

    Logger LOGGER = LoggerFactory.getLogger(InnerRestResultHandler.class);

    /**
     * 404 400 etc.
     * @param fe
     */
    default void doFeignException(FeignException fe){
        // do nothing
        throw fe;
    }

    /**
     * Service Producer Exception
     * @param restResult
     * @param targetClass
     * @param targetMethod
     * @param argsJsonString
     */
    default void doInnerException(RestResult restResult, Class targetClass, String targetMethod, String argsJsonString){
        // 远程调用异常记录
        int code = restResult.getStatus().getCode();
        String msg = restResult.getStatus().getMsg();
        if (RestStatus.CODE_SUCCESS != code) {
            LOGGER.info("restException:{},status:{},message:{},args:{}", targetClass + ":" + targetMethod,
                    code, msg, argsJsonString);
            throw new InnerServiceException(code, msg);
        }
    }

}

