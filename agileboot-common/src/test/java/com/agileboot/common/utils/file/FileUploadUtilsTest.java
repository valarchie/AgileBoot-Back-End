package com.agileboot.common.utils.file;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.config.AgileBootConfig;
import com.agileboot.common.constant.Constants.UploadSubDir;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.common.exception.error.ErrorCode.Internal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

class FileUploadUtilsTest {

    @Test
    void testIsAllowedExtension() {
        String[] imageTypes = new String[]{"img", "gif"};

        boolean isAllow = FileUploadUtils.isExtensionAllowed("img", imageTypes);
        boolean isNotAllow = FileUploadUtils.isExtensionAllowed("png", imageTypes);

        Assertions.assertTrue(isAllow);
        Assertions.assertFalse(isNotAllow);
    }

    @Test
    void testIsAllowedExtensionWhenNull() {
        String[] imageTypes = null;

        boolean isAllow = FileUploadUtils.isExtensionAllowed("img", imageTypes);

        Assertions.assertTrue(isAllow);
    }

    @Test
    void testGetRelativeFileUrl() {
        String relativeFilePath = FileUploadUtils.getRelativeFileUrl(UploadSubDir.UPLOAD_PATH, "test.jpg");

        Assertions.assertEquals("/profile/upload/test.jpg", relativeFilePath);
    }

    @Test
    void testSaveFileToLocal() {
        MultipartFile fileMock = Mockito.mock(MultipartFile.class);

        ApiException exceptionWithNullSubDir = Assertions.assertThrows(ApiException.class,
            () -> FileUploadUtils.saveFileToLocal(fileMock, "", ""));
        ApiException exceptionWitEmptyFileName = Assertions.assertThrows(ApiException.class,
            () -> FileUploadUtils.saveFileToLocal(fileMock, "", ""));

        Assertions.assertEquals(Internal.INVALID_PARAMETER, exceptionWithNullSubDir.getErrorCode());
        Assertions.assertEquals(Internal.INVALID_PARAMETER, exceptionWitEmptyFileName.getErrorCode());
    }

    @Test
    void testIsAllowedUploadWhenFileNameTooLong() {
        MultipartFile fileMock = Mockito.mock(MultipartFile.class);
        String longFileName = "this is a very very long sentence, this is a very very long sentence, "
            + "this is a very very long sentence, this is a very very long sentence, ";

        Mockito.when(fileMock.getOriginalFilename()).thenReturn(longFileName);

        ApiException exception = Assertions.assertThrows(ApiException.class,
            () -> FileUploadUtils.isAllowedUpload(fileMock, null));
        Assertions.assertEquals(Business.UPLOAD_FILE_NAME_EXCEED_MAX_LENGTH, exception.getErrorCode());
    }

    @Test
    void testIsAllowedUploadWhenFileTooBig() {
        MultipartFile fileMock = Mockito.mock(MultipartFile.class);
        Mockito.when(fileMock.getOriginalFilename()).thenReturn("test.jpg");
        Mockito.when(fileMock.getSize()).thenReturn(FileUploadUtils.MAX_FILE_SIZE + 1);

        ApiException exception = Assertions.assertThrows(ApiException.class,
            () -> FileUploadUtils.isAllowedUpload(fileMock, null));
        Assertions.assertEquals(Business.UPLOAD_FILE_SIZE_EXCEED_MAX_SIZE, exception.getErrorCode());
    }

    @Test
    void testisAllowDownload() {
        Assertions.assertFalse(FileUploadUtils.isAllowDownload("../test.jpg"));
        Assertions.assertFalse(FileUploadUtils.isAllowDownload("../test.exe"));
    }

    @Test
    void testGetFileExtension() {
        MultipartFile fileMock = Mockito.mock(MultipartFile.class);
        Mockito.when(fileMock.getOriginalFilename()).thenReturn("test.jpg");

        String fileExtension = FileUploadUtils.getFileExtension(fileMock);

        Assertions.assertEquals("jpg", fileExtension);
    }

    @Test
    void testGenerateFilename() {
        String fileName = "test.jpg";
        MultipartFile fileMock = Mockito.mock(MultipartFile.class);
        Mockito.when(fileMock.getOriginalFilename()).thenReturn(fileName);

        String randomFileName = FileUploadUtils.generateFilename(fileMock);

        String[] nameParts = randomFileName.split("_");
        Assertions.assertEquals("test", nameParts[1]);
        Assertions.assertTrue(StrUtil.endWith(nameParts[2], ".jpg"));
    }


    @Test
    void getFileAbsolutePath() {
        AgileBootConfig agileBootConfig = new AgileBootConfig();
        agileBootConfig.setFileBaseDir("D:\\agileboot");

        String fileAbsolutePath = FileUploadUtils.getFileAbsolutePath(UploadSubDir.AVATAR_PATH, "test.jpg");

        Assertions.assertEquals("D:\\agileboot\\profile\\avatar\\test.jpg", fileAbsolutePath);
    }
}
