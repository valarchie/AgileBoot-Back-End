package com.agileboot.common.exception.error;

import com.agileboot.common.exception.error.ErrorCode.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ErrorCodeInterfaceTest {

    @Test
    void testI18nKey() {
        String i18nKey = Client.COMMON_FORBIDDEN_TO_CALL.i18nKey();
        Assertions.assertEquals("20001_COMMON_FORBIDDEN_TO_CALL", i18nKey);
    }
}
