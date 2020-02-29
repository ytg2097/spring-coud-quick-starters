package io.ac.starter.operatorid;

import common.exception.BusinessException;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
public class OperatorIdCannotBeNullException extends BusinessException {

    public static final int OPERATOR_ID_CANNOT_BE_NULL = 20000;

    public OperatorIdCannotBeNullException() {
        super(OPERATOR_ID_CANNOT_BE_NULL, "Operator id cannot be null in header.");
    }
}
