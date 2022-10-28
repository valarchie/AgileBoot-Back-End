package com.agileboot.integrationtest.db;

import com.agileboot.integrationtest.IntegrationTestApplication;
import com.agileboot.orm.enums.SystemConfigEnum;
import com.agileboot.orm.service.ISysConfigService;
import com.agileboot.orm.service.ISysDeptService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = IntegrationTestApplication.class)
@RunWith(SpringRunner.class)
class SysDeptServiceImplTest {

    @Autowired
    ISysDeptService deptService;

    @Test
    @Rollback
    void testCheckDeptNameUnique() {
        boolean addWithSameName = deptService.checkDeptNameUnique("AgileBoot科技", null, null);
        boolean updateWithSameName = deptService.checkDeptNameUnique("AgileBoot科技", 100L, null);
        boolean addSameNameInParent = deptService.checkDeptNameUnique("深圳总公司", null, 100L);

    }


}
