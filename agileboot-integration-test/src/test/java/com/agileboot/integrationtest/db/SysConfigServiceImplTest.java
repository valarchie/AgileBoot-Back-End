package com.agileboot.integrationtest.db;

import com.agileboot.integrationtest.IntegrationTestApplication;
import com.agileboot.orm.enums.ConfigKeyEnum;
import com.agileboot.orm.service.ISysConfigService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = IntegrationTestApplication.class)
@RunWith(SpringRunner.class)
class SysConfigServiceImplTest {

    @Autowired
    ISysConfigService configService;

    @Test
    void testGetConfigValueByKey() {
        String configValue = configService.getConfigValueByKey(ConfigKeyEnum.CAPTCHA.getValue());
        Assertions.assertFalse(Boolean.parseBoolean(configValue));
    }


}
