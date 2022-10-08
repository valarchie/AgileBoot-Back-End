package com.agileboot.common.utils.ip;

/**
 * @author valarchie
 */
public class IpRegionUtil {

    public static IpRegion getIpRegion(String ip) {
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
