package io.ac.starter.tenant;

import common.exception.BusinessException;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
public class TenantCannotBeNullException extends BusinessException {

    public static final int TID_CANNOT_BE_NULL_ERROR = 20001;
    public TenantCannotBeNullException() {
        super(TID_CANNOT_BE_NULL_ERROR, "Tenant id cannot be null in header.");
    }
}
