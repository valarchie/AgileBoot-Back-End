package com.agileboot.infrastructure.web.service;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.agileboot.common.constant.Constants.Token;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * token验证处理
 *
 * @author valarchie
 */
@Component
@Slf4j
@RequiredArgsConstructor
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
     * 自动刷新token的时间
     */
    @Value("${token.autoRefreshTime}")
    private long autoRefreshTime;

    @NonNull
    private RedisCacheService redisCache;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getTokenFromRequest(request);
        if (StrUtil.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Token.LOGIN_USER_KEY);

                return redisCache.loginUserCache.getObjectOnlyInCacheById(uuid);
            } catch (Exception e) {
                log.error("fail to get cached user from redis", e);
                throw new ApiException(e, ErrorCode.UNKNOWN_ERROR);
            }
        }
        return null;
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createTokenAndPutUserInCache(LoginUser loginUser) {
        loginUser.setToken(IdUtil.fastUUID());

        redisCache.loginUserCache.set(loginUser.getToken(), loginUser);

        return generateToken(MapUtil.of(Token.LOGIN_USER_KEY, loginUser.getToken()));
    }

    /**
     * 当过期时间不足20分钟，自动刷新token
     * @param loginUser 登录用户
     */
    public void refreshToken(LoginUser loginUser) {
        long currentTime = System.currentTimeMillis();
        if (loginUser.getExpireTime() - currentTime <= TimeUnit.MINUTES.toMillis(autoRefreshTime)) {
            loginUser.refreshExpireTime(currentTime);
            // 根据uuid将loginUser存入缓存
            redisCache.loginUserCache.set(loginUser.getToken(), loginUser);
        }
    }


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
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
    private String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 获取请求token
     *
     * @return token
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StrUtil.isNotEmpty(token) && token.startsWith(Token.TOKEN_PREFIX)) {
            token = StrUtil.stripIgnoreCase(token, Token.TOKEN_PREFIX, null);
        }
        return token;
    }

}
