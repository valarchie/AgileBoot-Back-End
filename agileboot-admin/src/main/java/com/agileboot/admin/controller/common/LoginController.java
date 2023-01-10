package com.agileboot.admin.controller.common;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.agileboot.admin.request.LoginDTO;
import com.agileboot.admin.response.UserPermissionDTO;
import com.agileboot.common.config.AgileBootConfig;
import com.agileboot.common.constant.Constants.Token;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.system.menu.MenuApplicationService;
import com.agileboot.domain.system.menu.dto.RouterDTO;
import com.agileboot.domain.system.user.command.AddUserCommand;
import com.agileboot.domain.system.user.dto.UserDTO;
import com.agileboot.infrastructure.annotations.RateLimit;
import com.agileboot.infrastructure.annotations.RateLimit.CacheType;
import com.agileboot.infrastructure.annotations.RateLimit.LimitType;
import com.agileboot.infrastructure.cache.map.MapCache;
import com.agileboot.infrastructure.security.AuthenticationUtils;
import com.agileboot.infrastructure.web.domain.login.CaptchaDTO;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.domain.ratelimit.RateLimitKey;
import com.agileboot.infrastructure.web.service.LoginService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author valarchie
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    @NonNull
    private LoginService loginService;

    @NonNull
    private MenuApplicationService menuApplicationService;

    @NonNull
    private AgileBootConfig agileBootConfig;

    /**
     * 访问首页，提示语
     */
    @GetMapping("/")
    @RateLimit(key = RateLimitKey.TEST_KEY, time = 10, maxCount = 5, cacheType = CacheType.Map,
        limitType = LimitType.GLOBAL)
    public String index() {
        return StrUtil.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。",
            agileBootConfig.getName(), agileBootConfig.getVersion());
    }

    /**
     * 生成验证码
     */
    @RateLimit(key = RateLimitKey.LOGIN_CAPTCHA_KEY, time = 10, maxCount = 10, cacheType = CacheType.REDIS,
        limitType = LimitType.IP)
    @GetMapping("/captchaImage")
    public ResponseDTO<CaptchaDTO> getCaptchaImg() {
        CaptchaDTO captchaImg = loginService.getCaptchaImg();
        return ResponseDTO.ok(captchaImg);
    }

    /**
     * 登录方法
     *
     * @param loginDTO 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public ResponseDTO<?> login(@RequestBody LoginDTO loginDTO) {
        // 生成令牌
        String token = loginService.login(loginDTO.getUsername(), loginDTO.getPassword(), loginDTO.getCode(),
            loginDTO.getUuid());

        return ResponseDTO.ok(MapUtil.of(Token.TOKEN_FIELD, token));
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getLoginUserInfo")
    public ResponseDTO<?> getLoginUserInfo() {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();

        UserPermissionDTO permissionDTO = new UserPermissionDTO();
        permissionDTO.setUser(new UserDTO(loginUser.getEntity()));
        permissionDTO.setRoleKey(loginUser.getRoleInfo().getRoleKey());
        permissionDTO.setPermissions(loginUser.getRoleInfo().getMenuPermissions());
        permissionDTO.setDictTypes(MapCache.dictionaryCache());

        return ResponseDTO.ok(permissionDTO);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public ResponseDTO<List<RouterDTO>> getRouters() {
        Long userId = AuthenticationUtils.getUserId();
        List<RouterDTO> routerTree = menuApplicationService.getRouterTree(userId);
        return ResponseDTO.ok(routerTree);
    }


    @PostMapping("/register")
    public ResponseDTO<?> register(@RequestBody AddUserCommand command) {
        return ResponseDTO.fail(Business.UNSUPPORTED_OPERATION);
    }

}
