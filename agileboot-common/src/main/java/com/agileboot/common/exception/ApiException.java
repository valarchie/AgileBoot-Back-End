package com.agileboot.common.exception;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.exception.error.ErrorCodeInterface;
import com.agileboot.common.utils.i18n.MessageUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author valarchie
 */
@Slf4j
public class ApiException extends RuntimeException{


    protected ErrorCodeInterface errorCode;

    protected String message;

    protected Object[] args;

    protected String formattedMessage;
    protected String i18nFormattedMessage;

    public ApiException(Throwable e, ErrorCodeInterface errorCode, Object... args) {
        super(e);
        fillErrorCode(errorCode, args);
    }

    public ApiException(Throwable e, ErrorCodeInterface errorCode) {
        super(e);
        fillErrorCode(errorCode);
    }

    public ApiException(ErrorCodeInterface errorCode, Object... args) {
        fillErrorCode(errorCode, args);
    }

    public ApiException(ErrorCodeInterface errorCode) {
        fillErrorCode(errorCode);
    }

    private void fillErrorCode(ErrorCodeInterface errorCode, Object... args) {
        this.errorCode = errorCode;
        this.message = errorCode.message();
        this.args = args;

        this.formattedMessage = StrUtil.format(this.message, args);

        try {
            this.i18nFormattedMessage = MessageUtils.message(errorCode.i18n(), args);
        } catch (Exception e) {
            log.error("could not found i18n error i18nMessage entry : " + e.getMessage());
        }

    }


    public ErrorCodeInterface getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodeInterface errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return i18nFormattedMessage != null ? i18nFormattedMessage : formattedMessage;
    }

    @Override
    public String getLocalizedMessage() {
        return i18nFormattedMessage;
    }

}
