package com.agileboot.infrastructure.web.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.agileboot.common.constant.Constants.Token;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.utils.ServletHolderUtil;
import com.agileboot.common.utils.ip.IpRegionUtil;
import com.agileboot.infrastructure.cache.redis.CacheKeyEnum;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import com.agileboot.infrastructure.web.domain.login.LoginInfo;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * token验证处理
 *
 * @author ruoyi
 */
@Component
@Slf4j
public class TokenService {

    /**
     * 自定义令牌标识
     */
    @Value("${token.header}")
    private String header;

    /**
     * 令牌秘钥
     */
    @Value("${token.secret}")
    private String secret;

    /**
     * 令牌有效期
     */
    @Value("${token.expireTime}")
    private int expireTime;

    /**
     * 自动刷新token的时间
     */
    @Value("${token.autoRefreshTime}")
    private long autoRefreshTime;

    @Autowired
    private RedisCacheService redisCacheService;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StrUtil.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Token.LOGIN_USER_KEY);

                return redisCacheService.loginUserCache.getCachedObjectById(uuid);
            } catch (Exception e) {
                log.error("fail to get cached user from redis", e);
                throw new ApiException(e, ErrorCode.UNKNOWN_ERROR);
            }
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser) {
        if (loginUser != null && StrUtil.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void deleteLoginUser(String token) {
        if (StrUtil.isNotEmpty(token)) {
            redisCacheService.loginUserCache.delete(token);
        }
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser) {
        String token = IdUtil.fastUUID();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Token.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新token
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= TimeUnit.MINUTES.toMillis(autoRefreshTime)) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + CacheKeyEnum.LOGIN_USER_KEY.timeUnit()
            .toMillis(CacheKeyEnum.LOGIN_USER_KEY.expiration()));
        // 根据uuid将loginUser缓存
        redisCacheService.loginUserCache.set(loginUser.getToken(), loginUser);

    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(LoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletHolderUtil.getRequest().getHeader("User-Agent"));
        String ip = ServletUtil.getClientIP(ServletHolderUtil.getRequest());
        if (loginUser.getLoginInfo() == null) {
            loginUser.setLoginInfo(new LoginInfo());
        }
        loginUser.getLoginInfo().setIpAddress(ip);
        loginUser.getLoginInfo().setLocation(IpRegionUtil.getBriefLocationByIp(ip));
        loginUser.getLoginInfo().setBrowser(userAgent.getBrowser().getName());
        loginUser.getLoginInfo().setOperationSystem(userAgent.getOperatingSystem().getName());
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 获取请求token
     *
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StrUtil.isNotEmpty(token) && token.startsWith(Token.TOKEN_PREFIX)) {
            token = StrUtil.stripIgnoreCase(token, Token.TOKEN_PREFIX, null);
        }
        return token;
    }

}
