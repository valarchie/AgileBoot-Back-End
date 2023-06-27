package com.agileboot.common.utils.ip;

import com.agileboot.common.config.AgileBootConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OnlineIpRegionUtilTest {

    @BeforeEach
    public void enableOnlineAddressQuery() {
        AgileBootConfig agileBootConfig = new AgileBootConfig();
        agileBootConfig.setAddressEnabled(true);
    }


    @Test
    void getIpRegionWithIpv6() {
        IpRegion region = Assertions.assertDoesNotThrow(() ->
                OnlineIpRegionUtil.getIpRegion("ABCD:EF01:2345:6789:ABCD:EF01:2345:6789")
        );
        Assertions.assertNull(region);
    }

    @Test
    void getIpRegionWithIpv4() {
        IpRegion ipRegion = OnlineIpRegionUtil.getIpRegion("120.42.247.130");

        Assertions.assertEquals("福建省", ipRegion.getProvince());
        Assertions.assertEquals("泉州市", ipRegion.getCity());
    }

    @Test
    void getIpRegionWithEmpty() {
        IpRegion region = Assertions.assertDoesNotThrow(() ->
                OnlineIpRegionUtil.getIpRegion("")
        );

        Assertions.assertNull(region);
    }


    @Test
    void getIpRegionWithNull() {
        IpRegion region = Assertions.assertDoesNotThrow(() ->
                OnlineIpRegionUtil.getIpRegion(null)
        );

        Assertions.assertNull(region);
    }

    @Test
    void getIpRegionWithWrongIpString() {
        IpRegion region = Assertions.assertDoesNotThrow(() ->
                OnlineIpRegionUtil.getIpRegion("seffsdfsdf")
        );

        Assertions.assertNull(region);
    }


}

