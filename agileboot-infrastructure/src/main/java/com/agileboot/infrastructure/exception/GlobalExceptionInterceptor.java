package com.agileboot.infrastructure.exception;

import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.common.exception.error.ErrorCode.Client;
import com.agileboot.common.exception.error.ErrorCode.Internal;
import com.google.common.util.concurrent.UncheckedExecutionException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author valarchie
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionInterceptor {

    /**
     * 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseDTO<?> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.error("请求地址'{}',权限校验失败'{}'", request.getRequestURI(), e.getMessage());
        return ResponseDTO.fail(new ApiException(Business.PERMISSION_NOT_ALLOWED_TO_OPERATE));
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseDTO<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
        HttpServletRequest request) {
        log.error("请求地址'{}',不支持'{}'请求", request.getRequestURI(), e.getMethod());
        return ResponseDTO.fail(new ApiException(Client.COMMON_REQUEST_METHOD_INVALID, e.getMethod()));
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ApiException.class)
    public ResponseDTO<?> handleServiceException(ApiException e) {
        log.error(e.getMessage(), e);
        return ResponseDTO.fail(e, e.getPayload());
    }

    /**
     * 捕获缓存类当中的错误
     */
    @ExceptionHandler(UncheckedExecutionException.class)
    public ResponseDTO<?> handleServiceException(UncheckedExecutionException e) {
        log.error(e.getMessage(), e);
        return ResponseDTO.fail(new ApiException(Internal.GET_CACHE_FAILED, e.getMessage()));
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseDTO<?> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String errorMsg = String.format("请求地址'%s',发生未知异常.", request.getRequestURI());
        log.error(errorMsg, e);
        return ResponseDTO.fail(new ApiException(Internal.INTERNAL_ERROR, e.getMessage()));
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseDTO<?> handleException(Exception e, HttpServletRequest request) {
        String errorMsg = String.format("请求地址'%s',发生未知异常.", request.getRequestURI());
        log.error(errorMsg, e);
        return ResponseDTO.fail(new ApiException(Internal.INTERNAL_ERROR, e.getMessage()));
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseDTO<?> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResponseDTO.fail(new ApiException(ErrorCode.Client.COMMON_REQUEST_PARAMETERS_INVALID, message));
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseDTO.fail(new ApiException(ErrorCode.Client.COMMON_REQUEST_PARAMETERS_INVALID, message));
    }


}
