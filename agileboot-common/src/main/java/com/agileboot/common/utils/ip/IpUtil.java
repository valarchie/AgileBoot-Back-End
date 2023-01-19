package com.agileboot.common.utils.ip;

import cn.hutool.core.lang.Validator;

/**
 * ip校验器
 * @author valarchie
 */
public class IpUtil {

    public static boolean isValidIpv4(String inetAddress) {
        return Validator.isIpv4(inetAddress);
    }

    public static boolean isValidIpv6(String inetAddress) {
        return Validator.isIpv6(inetAddress);
    }

}
