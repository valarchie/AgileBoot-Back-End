package com.agileboot.infrastructure.security;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * Rsa key生成
 * @author valarchie
 */
public class RsaKeyPairGenerator {

  public static void main(String[] args) {
        RSA rsa = SecureUtil.rsa();

        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        String publicKeyBase64 = rsa.getPublicKeyBase64();

        System.out.println(privateKeyBase64);
        System.out.println(publicKeyBase64);
    }


}
