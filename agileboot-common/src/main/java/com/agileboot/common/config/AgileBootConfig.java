package com.agileboot.common.config;

import com.agileboot.common.constant.Constants.UploadDir;
import java.io.File;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author valarchie
 */
@Component
@ConfigurationProperties(prefix = "agileboot")
@Data
public class AgileBootConfig {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;

    /**
     * 版权年份
     */
    private String copyrightYear;

    /**
     * 实例演示开关
     */
    private boolean demoEnabled;

    /**
     * 上传路径
     */
    private static String fileBaseDir;

    /**
     * 获取地址开关
     */
    private static boolean addressEnabled;

    /**
     * 验证码类型
     */
    private static String captchaType;

    /**
     * rsa private key  静态属性的注入！！ set方法一定不能是static 方法
     */
    private static String rsaPrivateKey;

    public static String getFileBaseDir() {
        return fileBaseDir;
    }

    public void setFileBaseDir(String fileBaseDir) {
        AgileBootConfig.fileBaseDir = fileBaseDir;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        AgileBootConfig.addressEnabled = addressEnabled;
    }

    public static String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        AgileBootConfig.captchaType = captchaType;
    }

    public static String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        AgileBootConfig.rsaPrivateKey = rsaPrivateKey;
    }

    /**
     * 获取导入上传路径
     */
    public static String getImportPath() {
        return getFileBaseDir() + File.separator + UploadDir.IMPORT_PATH;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath() {
        return getFileBaseDir() + File.separator + UploadDir.AVATAR_PATH;
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath() {
        return getFileBaseDir() +  File.separator  + UploadDir.DOWNLOAD_PATH;
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath() {
        return getFileBaseDir() + File.separator + UploadDir.UPLOAD_PATH;
    }
}
