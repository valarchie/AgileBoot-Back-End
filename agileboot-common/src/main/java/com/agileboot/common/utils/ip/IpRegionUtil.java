package com.agileboot.common.utils.ip;

import cn.hutool.core.util.StrUtil;

/**
 * IP地理位置工具类
 *
 * @author valarchie
 */
public class IpRegionUtil {

    private IpRegionUtil() {
    }

    public static IpRegion getIpRegion(String ip) {
        if (StrUtil.isEmpty(ip)) {
            return new IpRegion();
        }

        if (IpUtil.isInnerIp(ip)) {
            return new IpRegion("", "内网IP");
        }

        IpRegion ipRegionOffline = OfflineIpRegionUtil.getIpRegion(ip);
        if (ipRegionOffline != null) {
            return ipRegionOffline;
        }

        IpRegion ipRegionOnline = OnlineIpRegionUtil.getIpRegion(ip);
        if (ipRegionOnline != null) {
            return ipRegionOnline;
        }

        return new IpRegion();
    }

    public static String getBriefLocationByIp(String ip) {
        return getIpRegion(ip).briefLocation();
    }

}
