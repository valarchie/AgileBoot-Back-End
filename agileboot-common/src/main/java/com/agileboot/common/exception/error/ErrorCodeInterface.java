package com.agileboot.common.exception.error;

public interface ErrorCodeInterface {

    String name();

    int code();

    String message();

    default String i18n() {
        return code() + "_" + name();
    }

}
