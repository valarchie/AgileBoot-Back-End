package com.agileboot.common.utils.file;

import org.junit.Assert;
import org.junit.Test;

public class FileUploadUtilsTest {

    @Test
    public void isAllowedExtension() {
        String[] imageTypes = new String[]{"img", "gif"};
        boolean isAllow = FileUploadUtils.isAllowedExtension("img", imageTypes);
        boolean isNotAllow = FileUploadUtils.isAllowedExtension("png", imageTypes);
        Assert.assertTrue(isAllow);
        Assert.assertFalse(isNotAllow);
    }
}
