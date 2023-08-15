package com.agileboot.infrastructure.web.util;

import com.agileboot.infrastructure.user.AuthenticationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthenticationUtilsTest {

    @Test
    void testMatchesPassword() {
        // 每次加密散列后的密码是不同的  但是代表的是同一份密码
        String password = "admin123";
        String encryptPassword1 = "$2a$10$vh0eep5P2Rj2x.mjjOSq/O1LT645Qwmohp.ciOA0.6E261rlOVSSO";
        String encryptPassword2 = "$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy";

        Assertions.assertTrue(AuthenticationUtils.matchesPassword(password,encryptPassword1));
        Assertions.assertTrue(AuthenticationUtils.matchesPassword(password,encryptPassword2));
    }

    @Test
    void testEncryptPassword() {
        String encrypt1 = AuthenticationUtils.encryptPassword("admin123");
        String encrypt2 = AuthenticationUtils.encryptPassword("admin123");

        Assertions.assertNotEquals(encrypt1, encrypt2);
    }
}
