package com.agileboot.infrastructure.web.domain.server;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.NumberUtil;
import com.agileboot.common.constant.Constants;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import lombok.Data;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

/**
 * 服务器相关信息
 *
 * @author ruoyi
 * @author valarchie
 */
@Data
public class ServerInfo {

    private static final int OSHI_WAIT_SECOND = 1000;

    /**
     * CPU相关信息
     */
    private CpuInfo cpuInfo = new CpuInfo();

    /**
     * 內存相关信息
     */
    private MemoryInfo memoryInfo = new MemoryInfo();

    /**
     * JVM相关信息
     */
    private JvmInfo jvmInfo = new JvmInfo();

    /**
     * 服务器相关信息
     */
    private SystemInfo systemInfo = new SystemInfo();

    /**
     * 磁盘相关信息
     */
    private List<DiskInfo> diskInfos = new LinkedList<DiskInfo>();

    public static ServerInfo fillInfo() {
        ServerInfo serverInfo = new ServerInfo();

        oshi.SystemInfo si = new oshi.SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        serverInfo.fillCpuInfo(hal.getProcessor());
        serverInfo.fillMemoryInfo(hal.getMemory());
        serverInfo.fillSystemInfo();
        serverInfo.fillJvmInfo();
        serverInfo.fillDiskInfos(si.getOperatingSystem());

        return serverInfo;
    }

    /**
     * 设置CPU信息
     */
    private void fillCpuInfo(CentralProcessor processor) {
        // CPU信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softIrq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long ioWait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + ioWait + irq + softIrq + steal;
        cpuInfo.setCpuNum(processor.getLogicalProcessorCount());
        cpuInfo.setTotal(totalCpu);
        cpuInfo.setSys(cSys);
        cpuInfo.setUsed(user);
        cpuInfo.setWait(ioWait);
        cpuInfo.setFree(idle);
    }

    /**
     * 设置内存信息
     */
    private void fillMemoryInfo(GlobalMemory memory) {
        memoryInfo.setTotal(memory.getTotal());
        memoryInfo.setUsed(memory.getTotal() - memory.getAvailable());
        memoryInfo.setFree(memory.getAvailable());
    }

    /**
     * 设置服务器信息
     */
    private void fillSystemInfo() {
        Properties props = System.getProperties();

        systemInfo.setComputerName(NetUtil.getLocalHostName());
        systemInfo.setComputerIp(NetUtil.getLocalhost().getHostAddress());
        systemInfo.setOsName(props.getProperty("os.name"));
        systemInfo.setOsArch(props.getProperty("os.arch"));
        systemInfo.setUserDir(props.getProperty("user.dir"));
    }

    /**
     * 设置Java虚拟机
     */
    private void fillJvmInfo() {
        Properties props = System.getProperties();
        jvmInfo.setTotal(Runtime.getRuntime().totalMemory());
        jvmInfo.setMax(Runtime.getRuntime().maxMemory());
        jvmInfo.setFree(Runtime.getRuntime().freeMemory());
        jvmInfo.setVersion(props.getProperty("java.version"));
        jvmInfo.setHome(props.getProperty("java.home"));
    }

    /**
     * 设置磁盘信息
     */
    private void fillDiskInfos(OperatingSystem os) {
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray) {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            DiskInfo diskInfo = new DiskInfo();
            diskInfo.setDirName(fs.getMount());
            diskInfo.setSysTypeName(fs.getType());
            diskInfo.setTypeName(fs.getName());
            diskInfo.setTotal(convertFileSize(total));
            diskInfo.setFree(convertFileSize(free));
            diskInfo.setUsed(convertFileSize(used));
            diskInfo.setUsage(NumberUtil.div(used * 100, total, 4));
            diskInfos.add(diskInfo);
        }
    }

    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public String convertFileSize(long size) {
        if (size >= Constants.GB) {
            return String.format("%.1f GB", (float) size / Constants.GB);
        }

        if (size >= Constants.MB) {
            float ratio = (float) size / Constants.MB;
            return String.format(ratio > 100 ? "%.0f MB" : "%.1f MB", Constants.MB);
        }

        if (size >= Constants.KB) {
            float ratio = (float) size / Constants.KB;
            return String.format(ratio > 100 ? "%.0f KB" : "%.1f KB", Constants.KB);
        }

        return String.format("%d B", size);

    }
}
