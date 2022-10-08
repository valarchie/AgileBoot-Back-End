package com.agileboot.infrastructure.aspectj;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.agileboot.common.utils.ServletHolderUtil;
import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.thread.AsyncTaskFactory;
import com.agileboot.infrastructure.thread.ThreadPoolManager;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.infrastructure.web.util.AuthenticationUtils;
import com.agileboot.orm.entity.SysOperationLogEntity;
import com.agileboot.orm.enums.RequestMethodEnum;
import com.agileboot.orm.enums.dictionary.OperationStatusEnum;
import com.agileboot.orm.enums.interfaces.BasicEnumUtil;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

/**
 * 操作日志记录处理
 *
 * @author valarchie
 */
@Aspect
@Component
@Slf4j
public class AccessLogAspect {


    /**
     * TODO 优化这个类 乱七八糟的
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, AccessLog controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, AccessLog controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, AccessLog controllerLog, final Exception e, Object jsonResult) {
        try {
            // 获取当前的用户
            LoginUser loginUser = AuthenticationUtils.getLoginUser();

            // *========数据库日志=========*//
            SysOperationLogEntity operationLog = new SysOperationLogEntity();
            operationLog.setStatus(OperationStatusEnum.SUCCESS.getValue());
            // 请求的地址

            String ip = ServletUtil.getClientIP(ServletHolderUtil.getRequest());
            operationLog.setOperatorIp(ip);
            operationLog.setRequestUrl(ServletHolderUtil.getRequest().getRequestURI());
            if (loginUser != null) {
                operationLog.setUsername(loginUser.getUsername());
            }

            if (e != null) {
                operationLog.setStatus(OperationStatusEnum.FAIL.getValue());

                operationLog.setErrorStack(StrUtil.sub(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operationLog.setCalledMethod(className + "." + methodName + "()");
            // 设置请求方式
            RequestMethodEnum requestMethodEnum = EnumUtil.fromString(RequestMethodEnum.class,
                ServletHolderUtil.getRequest().getMethod());
            operationLog.setRequestMethod(requestMethodEnum != null ? requestMethodEnum.getValue() : RequestMethodEnum.UNKNOWN.getValue());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operationLog, jsonResult);
            operationLog.setOperationTime(DateUtil.date());
            // 保存数据库
            ThreadPoolManager.execute(AsyncTaskFactory.recordOperationLog(operationLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log 日志
     * @param operationLog 操作日志
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, AccessLog log,
        SysOperationLogEntity operationLog, Object jsonResult)
        throws Exception {
        // 设置action动作
        operationLog.setBusinessType(log.businessType().ordinal());
        // 设置标题
        operationLog.setRequestModule(log.title());
        // 设置操作人类别
        operationLog.setOperatorType(log.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operationLog);
        }
        // 是否需要保存response，参数和值
        if (log.isSaveResponseData() && jsonResult != null) {
            operationLog.setOperationResult(StrUtil.sub(JSONUtil.toJsonStr(jsonResult), 0, 2000));
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operationLog 操作日志
     */
    private void setRequestValue(JoinPoint joinPoint, SysOperationLogEntity operationLog) {

        RequestMethodEnum requestMethodEnum = BasicEnumUtil.fromValue(RequestMethodEnum.class,
            operationLog.getRequestMethod());

        if (requestMethodEnum == RequestMethodEnum.GET || requestMethodEnum == RequestMethodEnum.POST) {
            String params = argsArrayToString(joinPoint.getArgs());
            operationLog.setOperationParam(StrUtil.sub(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletHolderUtil.getRequest()
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operationLog.setOperationParam(StrUtil.sub(paramsMap.toString(), 0, 2000));
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (o != null && !isFilterObject(o)) {
                    try {
                        Object jsonObj = JSONUtil.parseObj(o);
                        params.append(jsonObj).append(" ");
                    } catch (Exception e) {
                        log.info("参数拼接错误", e);
                    }
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
            || o instanceof BindingResult;
    }


}
