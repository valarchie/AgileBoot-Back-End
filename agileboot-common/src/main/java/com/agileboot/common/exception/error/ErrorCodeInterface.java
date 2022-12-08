package com.agileboot.common.exception.error;

/**
 * @author valarchie
 */
public interface ErrorCodeInterface {

    /**
     * 返回错误码名称
     * @return 枚举名称
     */
    String name();

    /**
     * 返回错误码
     * @return 错误码
     */
    int code();

    /**
     * 返回具体的详细错误描述
     * @return 错误描述
     */
    String message();

    /**
     * i18n资源文件的key, 详见messages.properties文件
     * @return key
     */
    default String i18nKey() {
        return code() + "_" + name();
    }

}
