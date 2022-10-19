package com.agileboot.common.utils.ip;

import org.junit.Assert;
import org.junit.Test;

public class OfflineIpRegionUtilTest {

    @Test
    public void testGetIpRegionWhenIpv4() {
        IpRegion ipRegion = OfflineIpRegionUtil.getIpRegion("110.81.189.80");

        Assert.assertEquals("中国", ipRegion.getCountry());
        Assert.assertEquals("福建省", ipRegion.getProvince());
        Assert.assertEquals("泉州市", ipRegion.getCity());
    }

    @Test
    public void testGetIpRegionWithIpv6() {
        IpRegion ipRegion = OfflineIpRegionUtil.getIpRegion("2001:0DB8:0000:0023:0008:0800:200C:417A");
        Assert.assertNull(ipRegion);
    }

    @Test
    public void testGetIpRegionWithEmpty() {
        IpRegion ipRegion = OfflineIpRegionUtil.getIpRegion("");
        Assert.assertNull(ipRegion);
    }

    @Test
    public void testGetIpRegionWithNull() {
        IpRegion ipRegion = OfflineIpRegionUtil.getIpRegion(null);
        Assert.assertNull(ipRegion);
    }
}
