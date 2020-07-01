package io.ac.starter.util;

import common.exception.BusinessException;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
public class RequestHeadCannotBeNullException extends BusinessException {

    public static final int TID_CANNOT_BE_NULL_ERROR = 20001;
    public RequestHeadCannotBeNullException(String headName) {
        super(TID_CANNOT_BE_NULL_ERROR, String.format("%s cannot be null in header.",headName));
    }
}
