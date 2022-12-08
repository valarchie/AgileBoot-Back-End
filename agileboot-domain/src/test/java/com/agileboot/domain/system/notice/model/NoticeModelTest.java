package com.agileboot.domain.system.notice.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NoticeModelTest {

    @Test
    void testCheckFieldsWhenTypeFailed() {
        NoticeModel noticeModel = new NoticeModel();
        noticeModel.setNoticeType(3);
        noticeModel.setStatus(1);

        ApiException exception = assertThrows(ApiException.class, noticeModel::checkFields);

        Assertions.assertEquals(ErrorCode.Internal.GET_ENUM_FAILED, exception.getErrorCode());
    }

    @Test
    void testCheckFieldsWhenTypeCorrect() {
        NoticeModel noticeModel = new NoticeModel();
        noticeModel.setNoticeType(1);
        noticeModel.setStatus(1);

        Assertions.assertDoesNotThrow(noticeModel::checkFields);
    }


    @Test
    void testCheckFieldsWhenStatusFailed() {
        NoticeModel noticeModel = new NoticeModel();
        noticeModel.setNoticeType(1);
        noticeModel.setStatus(3);

        ApiException exception = assertThrows(ApiException.class, noticeModel::checkFields);

        Assertions.assertEquals(ErrorCode.Internal.GET_ENUM_FAILED, exception.getErrorCode());
    }

}
