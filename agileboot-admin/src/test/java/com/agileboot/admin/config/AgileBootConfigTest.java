package com.agileboot.admin.config;


import com.agileboot.admin.AgileBooAdminApplication;
import com.agileboot.common.config.AgileBootConfig;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = AgileBooAdminApplication.class)
@RunWith(SpringRunner.class)
public class AgileBootConfigTest {

    @Autowired
    private AgileBootConfig config;

    @Test
    public void testConfig() {

        String fileBaseDir = "D:\\agileboot\\uploadPath";

        Assertions.assertEquals("AgileBoot", config.getName());
        Assertions.assertEquals("1.0.0", config.getVersion());
        Assertions.assertEquals("2022", config.getCopyrightYear());
        Assertions.assertEquals(true, config.isDemoEnabled());
        Assertions.assertEquals(fileBaseDir, AgileBootConfig.getFileBaseDir());
        Assertions.assertEquals(false, AgileBootConfig.isAddressEnabled());
        Assertions.assertEquals("math", AgileBootConfig.getCaptchaType());
        Assertions.assertEquals("math", AgileBootConfig.getCaptchaType());
        Assertions.assertEquals(fileBaseDir + "\\import", AgileBootConfig.getImportPath());
        Assertions.assertEquals(fileBaseDir + "\\avatar", AgileBootConfig.getAvatarPath());
        Assertions.assertEquals(fileBaseDir + "\\download", AgileBootConfig.getDownloadPath());
        Assertions.assertEquals(fileBaseDir + "\\upload", AgileBootConfig.getUploadPath());
    }

}
