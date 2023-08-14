package com.agileboot.admin.customize.async;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.agileboot.common.utils.ServletHolderUtil;
import com.agileboot.common.utils.ip.IpRegionUtil;
import com.agileboot.common.enums.common.LoginStatusEnum;
import com.agileboot.domain.system.log.db.SysLoginInfoEntity;
import com.agileboot.domain.system.log.db.SysOperationLogEntity;
import com.agileboot.domain.system.log.db.SysLoginInfoService;
import com.agileboot.domain.system.log.db.SysOperationLogService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步工厂（产生任务用）
 *
 * @author ruoyi
 */
@Slf4j
public class AsyncTaskFactory {

    private AsyncTaskFactory() {
    }

    /**
     * 记录登录信息
     *
     * @param username        用户名
     * @param loginStatusEnum 状态
     * @param message         消息
     * @return 任务task
     */
    public static Runnable loginInfoTask(final String username, final LoginStatusEnum loginStatusEnum, final String message) {
        // 优化一下这个类
        final UserAgent userAgent = UserAgent.parseUserAgentString(
            ServletHolderUtil.getRequest().getHeader("User-Agent"));
        // 获取客户端浏览器
        final String browser = userAgent.getBrowser() != null ? userAgent.getBrowser().getName() : "";
        final String ip = ServletUtil.getClientIP(ServletHolderUtil.getRequest());
        final String address = IpRegionUtil.getBriefLocationByIp(ip);
        // 获取客户端操作系统
        final String os = userAgent.getOperatingSystem() != null ? userAgent.getOperatingSystem().getName() : "";

        log.info("ip: {}, address: {}, username: {}, loginStatusEnum: {}, message: {}", ip, address, username,
            loginStatusEnum, message);
        return () -> {
            // 封装对象
            SysLoginInfoEntity loginInfo = new SysLoginInfoEntity();
            loginInfo.setUsername(username);
            loginInfo.setIpAddress(ip);
            loginInfo.setLoginLocation(address);
            loginInfo.setBrowser(browser);
            loginInfo.setOperationSystem(os);
            loginInfo.setMsg(message);
            loginInfo.setLoginTime(DateUtil.date());
            loginInfo.setStatus(loginStatusEnum.getValue());
            // 插入数据
            SpringUtil.getBean(SysLoginInfoService.class).save(loginInfo);
        };
    }

    /**
     * 操作日志记录
     *
     * @param operationLog 操作日志信息
     * @return 任务task
     */
    public static Runnable recordOperationLog(final SysOperationLogEntity operationLog) {
        return () -> {
            // 远程查询操作地点
            operationLog.setOperatorLocation(IpRegionUtil.getBriefLocationByIp(operationLog.getOperatorIp()));
            SpringUtil.getBean(SysOperationLogService.class).save(operationLog);
        };
    }

}
