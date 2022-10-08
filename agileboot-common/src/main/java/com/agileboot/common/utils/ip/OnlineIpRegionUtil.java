package com.agileboot.common.utils.ip;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.agileboot.common.config.AgileBootConfig;
import com.agileboot.common.utils.jackson.JacksonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * query geography address from ip
 *
 * @author valarchie
 */
@Slf4j
public class OnlineIpRegionUtil {

    /**
     * website for query geography address from ip
     */
    public static final String ADDRESS_QUERY_SITE = "http://whois.pconline.com.cn/ipJson.jsp";


    public static IpRegion getIpRegion(String ip) {
        // no need to query address for inner ip
        if (NetUtil.isInnerIP(ip)) {
            return new IpRegion("internal", "IP");
        }
        if (AgileBootConfig.isAddressEnabled()) {
            try {
                String rspStr = HttpUtil.get(ADDRESS_QUERY_SITE + "ip=" + ip + "&json=true",
                    CharsetUtil.CHARSET_GBK);
                if (StrUtil.isEmpty(rspStr)) {
                    log.error("获取地理位置异常 {}", ip);
                    return null;
                }

                String province = JacksonUtil.getAsString(rspStr, "pro");
                String city = JacksonUtil.getAsString(rspStr, "city");
                return new IpRegion(province, city);
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return null;
    }

}
