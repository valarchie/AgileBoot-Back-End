package com.agileboot.infrastructure.security.handle;

import cn.hutool.json.JSONUtil;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.exception.error.ErrorCode.Client;
import com.agileboot.common.utils.ServletHolderUtil;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 认证失败处理类 返回未授权
 *
 * @author ruoyi
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        ResponseDTO<Object> responseDTO = ResponseDTO.fail(Client.COMMON_NO_AUTHORIZATION, request.getRequestURI());
        ServletHolderUtil.renderString(response, JSONUtil.toJsonStr(responseDTO));
    }
}
