package com.agileboot.common.utils.ip;

import org.junit.Assert;
import org.junit.Test;

public class OfflineIpRegionUtilTest {

    @Test
    public void getIpRegion() {
        IpRegion ipRegion = OfflineIpRegionUtil.getIpRegion("110.81.189.80");

        Assert.assertEquals("中国", ipRegion.getCountry());
        Assert.assertEquals("福建省", ipRegion.getProvince());
        Assert.assertEquals("泉州市", ipRegion.getCity());
    }
}
