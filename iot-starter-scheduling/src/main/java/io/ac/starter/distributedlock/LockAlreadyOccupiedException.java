package io.ac.starter.distributedlock;

import common.exception.SystemException;

import static io.ac.starter.exception.ErrorCodes.LOCK_OCCUPIED;


/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-21
 **/
public class LockAlreadyOccupiedException extends SystemException {

    public LockAlreadyOccupiedException(String taskName) {
        super(LOCK_OCCUPIED, String.format("任务已被锁定，请稍后重试 ,任务名称: [%s]",taskName));
    }
}
