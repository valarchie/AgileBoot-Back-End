package com.agileboot.common.exception.error;

public interface ErrorCodeInterface {

    /**
     * @return 返回枚举名称
     */
    String name();

    /**
     * 返回错误码
     * @return 错误码
     */
    int code();

    /**
     *
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
