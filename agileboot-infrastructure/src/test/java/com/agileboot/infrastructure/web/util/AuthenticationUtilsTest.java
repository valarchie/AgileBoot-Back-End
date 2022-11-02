package com.agileboot.infrastructure.web.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthenticationUtilsTest {

    @Test
    void testMatchesPassword() {
        // 每次加密后的密码是不同的  但是代表的是同一份密码
        boolean match1 = AuthenticationUtils.matchesPassword("admin123",
            "$2a$10$vh0eep5P2Rj2x.mjjOSq/O1LT645Qwmohp.ciOA0.6E261rlOVSSO");

        boolean match2 = AuthenticationUtils.matchesPassword("admin123",
            "$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy");

        Assertions.assertEquals(true, match1);
        Assertions.assertEquals(true, match2);
    }

    @Test
    void testEncryptPassword() {
        String encrypt1 = AuthenticationUtils.encryptPassword("admin123");
        String encrypt2 = AuthenticationUtils.encryptPassword("admin123");

        Assertions.assertNotEquals(encrypt1, encrypt2);
    }
}
