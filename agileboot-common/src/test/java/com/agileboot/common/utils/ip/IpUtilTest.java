package com.agileboot.common.utils.ip;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IpUtilTest {

    @Test
    void isInnerIp() {
        boolean innerIp1 = IpUtil.isLocalHost("127.0.0.1");
        boolean innerIp2 = IpUtil.isLocalHost("0:0:0:0:0:0:0:1");
        boolean innerIp3 = IpUtil.isLocalHost("localhost");
        boolean innerIp4 = IpUtil.isLocalHost("192.168.1.1");
        boolean innerIp5 = IpUtil.isLocalHost("10.32.1.1");
        boolean innerIp6 = IpUtil.isLocalHost("172.16.1.1");
        boolean notInnerIP = IpUtil.isLocalHost("110.81.189.80");

        Assertions.assertTrue(innerIp1);
        Assertions.assertTrue(innerIp2);
        Assertions.assertTrue(innerIp3);
        Assertions.assertTrue(innerIp4);
        Assertions.assertTrue(innerIp5);
        Assertions.assertTrue(innerIp6);
        Assertions.assertFalse(notInnerIP);
    }

    @Test
    void isLocalHost() {
        boolean localHost1 = IpUtil.isLocalHost("127.0.0.1");
        boolean localHost2 = IpUtil.isLocalHost("0:0:0:0:0:0:0:1");
        boolean localHost4 = IpUtil.isLocalHost("localhost");
        boolean notLocalHost = IpUtil.isLocalHost("110.81.189.80");

        Assertions.assertTrue(localHost1);
        Assertions.assertTrue(localHost2);
        Assertions.assertTrue(localHost4);
        Assertions.assertFalse(notLocalHost);
    }

}
