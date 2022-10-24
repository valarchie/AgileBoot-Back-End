package com.agileboot.common.utils.ip;

import org.apache.commons.validator.routines.InetAddressValidator;

/**
 * ip校验器
 * @author valarchie
 */
public class IpUtil {

    private static final InetAddressValidator VALIDATOR = InetAddressValidator.getInstance();

    public static boolean isValidIpv4(String inetAddress) {
        return VALIDATOR.isValidInet4Address(inetAddress);
    }

    public static boolean isValidIpv6(String inetAddress) {
        return VALIDATOR.isValidInet6Address(inetAddress);
    }

}
