package com.agileboot.common.exception.error;

public interface ErrorCodeInterface {

    String name();

    int code();

    String message();

    default String i18nKey() {
        return code() + "_" + name();
    }

}
