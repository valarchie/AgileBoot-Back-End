package com.agileboot.common.exception;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.exception.error.ErrorCodeInterface;
import com.agileboot.common.utils.i18n.MessageUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常类
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
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
            this.i18nFormattedMessage = MessageUtils.message(errorCode.i18nKey(), args);
        } catch (Exception e) {
            log.error("could not found i18nMessage entry for key: " + errorCode.i18nKey());
        }

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
