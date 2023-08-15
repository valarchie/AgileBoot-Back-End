package com.agileboot.api.customize.config;

import com.agileboot.api.customize.service.JwtTokenService;
import com.agileboot.infrastructure.user.app.AppLoginUser;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * token过滤器 验证token有效性
 * 继承OncePerRequestFilter类的话  可以确保只执行filter一次， 避免执行多次
 * @author valarchie
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenFromRequest = jwtTokenService.getTokenFromRequest(request);

        if (tokenFromRequest != null) {
            Claims claims = jwtTokenService.parseToken(tokenFromRequest);
            String token = (String) claims.get("token");
            // 根据token去查缓存里面 有没有对应的App用户
            // 没有的话  再去数据库中查询
            if (token != null && token.equals("user1")) {
                AppLoginUser loginUser = new AppLoginUser(23232323L, false, "dasdsadsds");
                loginUser.grantAppPermission("annie");
                UsernamePasswordAuthenticationToken suer1 = new UsernamePasswordAuthenticationToken(loginUser, null,
                    loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(suer1);
            }
        }

        filterChain.doFilter(request, response);
    }
}
