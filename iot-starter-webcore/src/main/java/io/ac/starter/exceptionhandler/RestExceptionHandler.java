package io.ac.starter.exceptionhandler;

import common.exception.BusinessException;
import common.exception.CommonErrorCodes;
import common.exception.SystemException;
import common.rest.RestResult;
import common.rest.RestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.stream.Collectors;

import static common.exception.CommonErrorCodes.VALIDATE_PARAMETERS_ERROR;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@RestControllerAdvice
public class RestExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 处理 spring @Validated 抛出异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResult handleSpringValidatedMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("\n"));

        RestStatus status = new RestStatus(VALIDATE_PARAMETERS_ERROR, msg);
        LOGGER.info(msg);
        return new RestResult(status);
    }

    /**
     * 处理 jsr303 @Valid 抛出异常
     */
    @ExceptionHandler(ValidationException.class)
    public RestResult handleJSR303ValidMethodArgumentNotValidException(ValidationException ex) {

        String msg = ex.getMessage();
        RestStatus status = new RestStatus(VALIDATE_PARAMETERS_ERROR, msg);
        LOGGER.info(msg);
        return new RestResult(status);
    }

    /**
     * 处理 BusinessException
     */
    @ExceptionHandler(BusinessException.class)
    public RestResult handleBusinessException(BusinessException ex) {
        RestStatus status = new RestStatus(ex.getCode(), ex.getMessage());
        LOGGER.info(ex.getMessage(), ex);
        return new RestResult(status);
    }

    /**
     * 处理 SystemException
     */
    @ExceptionHandler(SystemException.class)
    public RestResult handleSystemException(SystemException ex) {
        RestStatus status = new RestStatus(ex.getCode(), ex.getMessage());
        LOGGER.info(ex.getMessage(), ex);
        return new RestResult(status);
    }

    /**
     * 处理 HttpMessageConversionException
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public RestResult handleHttpMessageConversionException(HttpMessageConversionException ex) {
        LOGGER.info(ex.getMessage(), ex);
        RestStatus status = new RestStatus(CommonErrorCodes.PARSE_PARAMETERS_ERROR, ex.getMessage());
        return new RestResult(status);
    }

    /**
     * 处理 ObjectOptimisticLockingFailureException
     */
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public RestResult handleObjectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException ex) {
        LOGGER.info(ex.getMessage(), ex);
        RestStatus status = new RestStatus(CommonErrorCodes.DATA_OUT_OF_DATE, "Data is out of date, please refresh and repeat.");
        return new RestResult(status);
    }

    /**
     * 处理 RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    public RestResult handleRuntimeException(RuntimeException ex) {
        LOGGER.error(ex.getMessage(), ex);
        RestStatus status = new RestStatus(CommonErrorCodes.UNKNOWN_ERROR, ex.getMessage());
        return new RestResult(status);
    }

    /**
     * 处理 OutOfMemoryError
     */
    @ExceptionHandler(OutOfMemoryError.class)
    public RestResult handleOomException(OutOfMemoryError ex) {
        LOGGER.error("OutOfMemoryError " + ex.getMessage(), ex);
        RestStatus status = new RestStatus(CommonErrorCodes.SYSTEM_ERROR, ex.getMessage());
        return new RestResult(status);
    }
}
