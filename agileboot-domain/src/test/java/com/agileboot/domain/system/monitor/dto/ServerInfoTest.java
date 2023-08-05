package com.agileboot.domain.system.monitor.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ServerInfoTest {

    @Test
    void testConvertFileSize() {
        ServerInfo serverInfo = new ServerInfo();

        Assertions.assertEquals("0.5 KB", serverInfo.convertFileSize(512L));
        Assertions.assertEquals("1.5 KB", serverInfo.convertFileSize(1536L));
        Assertions.assertEquals("1.5 MB", serverInfo.convertFileSize(1572864));
        Assertions.assertEquals("1.5 GB", serverInfo.convertFileSize(1610612736L));
    }
}
