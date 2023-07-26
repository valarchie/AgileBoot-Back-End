package com.agileboot.common.exception;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.exception.error.ErrorCodeInterface;
import com.agileboot.common.utils.i18n.MessageUtils;
import java.util.HashMap;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常类
 *
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class ApiException extends RuntimeException {

    protected ErrorCodeInterface errorCode;

    protected String message;

    protected String i18nMessage;

    /**
     * 如果有一些特殊的数据  可以放在这个payload里面
     * 有时候错误的返回信息太少  不便前端处理的话  可以放在这个payload字段当中
     * 比如你做了一个大批量操作，操作ID为1~10的实体， 其中1~5成功   6~10失败
     * 你可以将这些相关信息放在这个payload中
     */
    protected HashMap<String, Object> payload;

    public ApiException(ErrorCodeInterface errorCode) {
        fillErrorCode(errorCode);
    }

    public ApiException(ErrorCodeInterface errorCode, Object... args) {
        fillErrorCode(errorCode, args);
    }

    /**
     * 注意  如果是try catch的情况下捕获异常 并转为ApiException的话  一定要填入Throwable e
     * @param e 捕获到的原始异常
     * @param errorCode 错误码
     * @param args 错误详细信息参数
     */
    public ApiException(Throwable e, ErrorCodeInterface errorCode, Object... args) {
        super(e);
        fillErrorCode(errorCode, args);
    }

    private void fillErrorCode(ErrorCodeInterface errorCode, Object... args) {
        this.errorCode = errorCode;
        this.message = StrUtil.format(errorCode.message(), args);

        try {
            this.i18nMessage = MessageUtils.message(errorCode.i18nKey(), args);
        } catch (Exception e) {
            log.error("could not found i18nMessage entry for key: " + errorCode.i18nKey());
        }
    }

    @Override
    public String getMessage() {
        return i18nMessage != null ? i18nMessage : message;
    }

    @Override
    public String getLocalizedMessage() {
        return i18nMessage != null ? i18nMessage : message;
    }

}
