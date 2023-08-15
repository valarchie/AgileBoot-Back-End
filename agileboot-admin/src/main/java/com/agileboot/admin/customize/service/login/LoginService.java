package com.agileboot.admin.customize.service.login;

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
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.common.utils.ServletHolderUtil;
import com.agileboot.common.utils.i18n.MessageUtils;
import com.agileboot.domain.common.cache.GuavaCacheService;
import com.agileboot.domain.common.cache.MapCache;
import com.agileboot.domain.common.cache.RedisCacheService;
import com.agileboot.admin.customize.async.AsyncTaskFactory;
import com.agileboot.infrastructure.thread.ThreadPoolManager;
import com.agileboot.admin.customize.service.login.dto.CaptchaDTO;
import com.agileboot.admin.customize.service.login.dto.ConfigDTO;
import com.agileboot.admin.customize.service.login.command.LoginCommand;
import com.agileboot.infrastructure.user.web.SystemLoginUser;
import com.agileboot.common.enums.common.ConfigKeyEnum;
import com.agileboot.common.enums.common.LoginStatusEnum;
import com.agileboot.domain.system.user.db.SysUserEntity;
import com.google.code.kaptcha.Producer;
import java.awt.image.BufferedImage;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

    private final TokenService tokenService;

    private final RedisCacheService redisCache;

    private final GuavaCacheService guavaCache;

    private final AuthenticationManager authenticationManager;

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * 登录验证
     *
     * @param loginCommand 登录参数
     * @return 结果
     */
    public String login(LoginCommand loginCommand) {
        // 验证码开关
        if (isCaptchaOn()) {
            validateCaptcha(loginCommand.getUsername(), loginCommand.getCaptchaCode(), loginCommand.getCaptchaCodeKey());
        }
        // 用户验证
        Authentication authentication;
        String decryptPassword = decryptPassword(loginCommand.getPassword());
        try {
            // 该方法会去调用UserDetailsServiceImpl#loadUserByUsername  校验用户名和密码  认证鉴权
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginCommand.getUsername(), decryptPassword));
        } catch (BadCredentialsException e) {
            ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(loginCommand.getUsername(), LoginStatusEnum.LOGIN_FAIL,
                MessageUtils.message("Business.LOGIN_WRONG_USER_PASSWORD")));
            throw new ApiException(e, ErrorCode.Business.LOGIN_WRONG_USER_PASSWORD);
        } catch (AuthenticationException e) {
            ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(loginCommand.getUsername(), LoginStatusEnum.LOGIN_FAIL, e.getMessage()));
            throw new ApiException(e, ErrorCode.Business.LOGIN_ERROR, e.getMessage());
        } catch (Exception e) {
            ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(loginCommand.getUsername(), LoginStatusEnum.LOGIN_FAIL, e.getMessage()));
            throw new ApiException(e, Business.LOGIN_ERROR, e.getMessage());
        }
        // 把当前登录用户 放入上下文中
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 这里获取的loginUser是UserDetailsServiceImpl#loadUserByUsername方法返回的LoginUser
        SystemLoginUser loginUser = (SystemLoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser);
        // 生成token
        return tokenService.createTokenAndPutUserInCache(loginUser);
    }

    /**
     * 获取验证码 data
     *
     * @return {@link ConfigDTO}
     */
    public ConfigDTO getConfig() {
        ConfigDTO configDTO = new ConfigDTO();

        boolean isCaptchaOn = isCaptchaOn();
        configDTO.setIsCaptchaOn(isCaptchaOn);
        configDTO.setDictionary(MapCache.dictionaryCache());
        return configDTO;
    }

    /**
     * 获取验证码 data
     *
     * @return 验证码
     */
    public CaptchaDTO generateCaptchaImg() {
        CaptchaDTO captchaDTO = new CaptchaDTO();

        boolean isCaptchaOn = isCaptchaOn();
        captchaDTO.setIsCaptchaOn(isCaptchaOn);

        if (isCaptchaOn) {
            String expression;
            String answer = null;
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
            String imgKey = IdUtil.simpleUUID();

            redisCache.captchaCache.set(imgKey, answer);
            // 转换流信息写出
            FastByteArrayOutputStream os = new FastByteArrayOutputStream();
            ImgUtil.writeJpg(image, os);

            captchaDTO.setCaptchaCodeKey(imgKey);
            captchaDTO.setCaptchaCodeImg(Base64.encode(os.toByteArray()));

        }

        return captchaDTO;
    }


    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param captchaCode 验证码
     * @param captchaCodeKey 验证码对应的缓存key
     */
    public void validateCaptcha(String username, String captchaCode, String captchaCodeKey) {
        String captcha = redisCache.captchaCache.getObjectById(captchaCodeKey);
        redisCache.captchaCache.delete(captchaCodeKey);
        if (captcha == null) {
            ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(username, LoginStatusEnum.LOGIN_FAIL,
                ErrorCode.Business.LOGIN_CAPTCHA_CODE_EXPIRE.message()));
            throw new ApiException(ErrorCode.Business.LOGIN_CAPTCHA_CODE_EXPIRE);
        }
        if (!captchaCode.equalsIgnoreCase(captcha)) {
            ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(username, LoginStatusEnum.LOGIN_FAIL,
                ErrorCode.Business.LOGIN_CAPTCHA_CODE_WRONG.message()));
            throw new ApiException(ErrorCode.Business.LOGIN_CAPTCHA_CODE_WRONG);
        }
    }

    /**
     * 记录登录信息
     * @param loginUser 登录用户
     */
    public void recordLoginInfo(SystemLoginUser loginUser) {
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
