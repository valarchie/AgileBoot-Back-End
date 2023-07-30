package com.agileboot.infrastructure.user;


import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.infrastructure.user.app.AppLoginUser;
import com.agileboot.infrastructure.user.web.SystemLoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全服务工具类
 *
 * @author valarchie
 */
public class AuthenticationUtils {

    private AuthenticationUtils() {}

    /**
     * 用户ID
     **/
    public static Long getUserId() {
        try {
            return getSystemLoginUser().getUserId();
        } catch (Exception e) {
            throw new ApiException(ErrorCode.Business.USER_FAIL_TO_GET_USER_ID);
        }
    }

    /**
     * 获取系统用户
     **/
    public static SystemLoginUser getSystemLoginUser() {
        try {
            return (SystemLoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ApiException(ErrorCode.Business.USER_FAIL_TO_GET_USER_INFO);
        }
    }

    /**
     * 获取App用户
     **/
    public static AppLoginUser getAppLoginUser() {
        try {
            return (AppLoginUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ApiException(ErrorCode.Business.USER_FAIL_TO_GET_USER_INFO);
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


}
