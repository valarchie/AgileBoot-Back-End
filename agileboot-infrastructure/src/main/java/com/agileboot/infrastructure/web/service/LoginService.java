package com.agileboot.infrastructure.web.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.extra.servlet.ServletUtil;
import com.agileboot.common.config.AgileBootConfig;
import com.agileboot.common.constant.Constants.Captcha;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.utils.ServletHolderUtil;
import com.agileboot.common.utils.i18n.MessageUtils;
import com.agileboot.infrastructure.cache.guava.GuavaCacheService;
import com.agileboot.infrastructure.cache.redis.RedisCacheService;
import com.agileboot.infrastructure.thread.AsyncTaskFactory;
import com.agileboot.infrastructure.thread.ThreadPoolManager;
import com.agileboot.infrastructure.web.domain.login.CaptchaDTO;
import com.agileboot.infrastructure.web.domain.login.LoginDTO;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.common.enums.ConfigKeyEnum;
import com.agileboot.orm.common.enums.LoginStatusEnum;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.google.code.kaptcha.Producer;
import java.awt.image.BufferedImage;
import javax.annotation.Resource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    @NonNull
    private TokenService tokenService;

    @NonNull
    private RedisCacheService redisCache;

    @NonNull
    private GuavaCacheService guavaCache;

    @NonNull
    private AuthenticationManager authenticationManager;

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * 登录验证
     *
     * @param loginDTO 登录参数
     * @return 结果
     */
    public String login(LoginDTO loginDTO) {
        // 验证码开关
        if (isCaptchaOn()) {
            validateCaptcha(loginDTO.getUsername(), loginDTO.getCode(), loginDTO.getUuid());
        }
        // 用户验证
        Authentication authentication;
        String decryptPassword = decryptPassword(loginDTO.getPassword());
        try {
            // 该方法会去调用UserDetailsServiceImpl#loadUserByUsername  校验用户名和密码  认证鉴权
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), decryptPassword));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(loginDTO.getUsername(), LoginStatusEnum.LOGIN_FAIL,
                    MessageUtils.message("user.password.not.match")));
                throw new ApiException(ErrorCode.Business.LOGIN_WRONG_USER_PASSWORD);
            } else {
                ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(loginDTO.getUsername(), LoginStatusEnum.LOGIN_FAIL, e.getMessage()));
                throw new ApiException(e.getCause(), ErrorCode.Business.LOGIN_ERROR, e.getMessage());
            }
        }
        // 把当前登录用户 放入上下文中
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 这里获取的loginUser是UserDetailsServiceImpl#loadUserByUsername方法返回的LoginUser
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser);
        // 生成token
        return tokenService.createTokenAndPutUserInCache(loginUser);
    }

    /**
     * 获取验证码 data
     * @return
     */
    public CaptchaDTO generateCaptchaImg() {
        CaptchaDTO captchaDTO = new CaptchaDTO();

        boolean isCaptchaOn = isCaptchaOn();
        captchaDTO.setIsCaptchaOn(isCaptchaOn);

        if (isCaptchaOn) {
            String expression, answer = null;
            BufferedImage image = null;

            // 生成验证码
            String captchaType = AgileBootConfig.getCaptchaType();
            if (Captcha.MATH_TYPE.equals(captchaType)) {
                String capText = captchaProducerMath.createText();
                String[] expressionAndAnswer = capText.split("@");
                expression = expressionAndAnswer[0];
                answer = expressionAndAnswer[1];
                image = captchaProducerMath.createImage(expression);
            }

            if (Captcha.CHAR_TYPE.equals(captchaType)) {
                expression = answer = captchaProducer.createText();
                image = captchaProducer.createImage(expression);
            }

            if (image == null) {
                throw new ApiException(ErrorCode.Internal.LOGIN_CAPTCHA_GENERATE_FAIL);
            }

            // 保存验证码信息
            String uuid = IdUtil.simpleUUID();

            redisCache.captchaCache.set(uuid, answer);
            // 转换流信息写出
            FastByteArrayOutputStream os = new FastByteArrayOutputStream();
            ImgUtil.writeJpg(image, os);

            captchaDTO.setUuid(uuid);
            captchaDTO.setImg(Base64.encode(os.toByteArray()));

        }

        return captchaDTO;
    }


    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String captcha = redisCache.captchaCache.getObjectById(uuid);
        redisCache.captchaCache.delete(uuid);
        if (captcha == null) {
            ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(username, LoginStatusEnum.LOGIN_FAIL,
                ErrorCode.Business.LOGIN_CAPTCHA_CODE_EXPIRE.message()));
            throw new ApiException(ErrorCode.Business.LOGIN_CAPTCHA_CODE_EXPIRE);
        }
        if (!code.equalsIgnoreCase(captcha)) {
            ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(username, LoginStatusEnum.LOGIN_FAIL,
                ErrorCode.Business.LOGIN_CAPTCHA_CODE_WRONG.message()));
            throw new ApiException(ErrorCode.Business.LOGIN_CAPTCHA_CODE_WRONG);
        }
    }

    /**
     * 记录登录信息
     * @param loginUser
     */
    public void recordLoginInfo(LoginUser loginUser) {
        ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(loginUser.getUsername(), LoginStatusEnum.LOGIN_SUCCESS,
            LoginStatusEnum.LOGIN_SUCCESS.description()));

        SysUserEntity entity = redisCache.userCache.getObjectById(loginUser.getUserId());

        entity.setLoginIp(ServletUtil.getClientIP(ServletHolderUtil.getRequest()));
        entity.setLoginDate(DateUtil.date());
        entity.updateById();
    }

    public String decryptPassword(String originalPassword) {
        byte[] decryptBytes = SecureUtil.rsa(AgileBootConfig.getRsaPrivateKey(), null)
            .decrypt(Base64.decode(originalPassword), KeyType.PrivateKey);

        return StrUtil.str(decryptBytes, CharsetUtil.CHARSET_UTF_8);
    }

    private boolean isCaptchaOn() {
        return Convert.toBool(guavaCache.configCache.get(ConfigKeyEnum.CAPTCHA.getValue()));
    }

}
