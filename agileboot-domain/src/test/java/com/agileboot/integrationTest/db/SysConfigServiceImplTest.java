package com.agileboot.integrationTest.db;

import com.agileboot.domain.system.config.db.SysConfigService;
import com.agileboot.integrationTest.IntegrationTestApplication;
import com.agileboot.common.enums.common.ConfigKeyEnum;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = IntegrationTestApplication.class)
@RunWith(SpringRunner.class)
class  SysConfigServiceImplTest {

    @Resource
    SysConfigService configService;

    @Test
    void testGetConfigValueByKey() {
        String configValue = configService.getConfigValueByKey(ConfigKeyEnum.CAPTCHA.getValue());
        Assertions.assertFalse(Boolean.parseBoolean(configValue));
    }


}
