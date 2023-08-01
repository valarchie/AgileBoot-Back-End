package com.agileboot.api.controller.common;

import cn.hutool.core.map.MapUtil;
import com.agileboot.api.customize.service.JwtTokenService;
import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 调度日志操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/common")
@AllArgsConstructor
public class LoginController extends BaseController {

    private final JwtTokenService jwtTokenService;

    /**
     * 访问首页，提示语
     */
    @PostMapping("/app/{appId}/login")
    public ResponseDTO<String> appLogin() {
        String token = jwtTokenService.generateToken(MapUtil.of("token", "user1"));
        return ResponseDTO.ok(token);
    }




}
