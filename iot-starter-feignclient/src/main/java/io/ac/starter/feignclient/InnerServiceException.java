package io.ac.starter.feignclient;


import common.exception.BusinessException;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
public class InnerServiceException extends BusinessException {
    public InnerServiceException(int code, String msg) {
        super(code,msg);
    }
}
