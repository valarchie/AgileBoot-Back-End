package com.agileboot.common.utils.ip;

import com.agileboot.common.config.AgileBootConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OnlineIpRegionUtilTest {

    @Before
    public void enableOnlineAddressQuery(){
        AgileBootConfig agileBootConfig = new AgileBootConfig();
        agileBootConfig.setAddressEnabled(true);
    }


    @Test
    public void getIpRegionWithIpv6() {
        IpRegion ipRegion = OnlineIpRegionUtil.getIpRegion("ABCD:EF01:2345:6789:ABCD:EF01:2345:6789");
        Assert.assertNull(ipRegion);
    }

    @Test
    public void getIpRegionWithIpv4() {
        IpRegion ipRegion = OnlineIpRegionUtil.getIpRegion("120.42.247.130");

        Assert.assertEquals("福建省", ipRegion.getProvince());
        Assert.assertEquals("泉州市", ipRegion.getCity());
    }

    @Test
    public void getIpRegionWithEmpty() {
        IpRegion ipRegion = OnlineIpRegionUtil.getIpRegion("");

        Assert.assertNull(ipRegion);
    }


    @Test
    public void getIpRegionWithNull() {
        IpRegion ipRegion = OnlineIpRegionUtil.getIpRegion(null);

        Assert.assertNull(ipRegion);
    }


}

