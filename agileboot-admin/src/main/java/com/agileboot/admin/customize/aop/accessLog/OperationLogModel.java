package com.agileboot.admin.customize.aop.accessLog;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.agileboot.common.utils.ServletHolderUtil;
import com.agileboot.infrastructure.user.AuthenticationUtils;
import com.agileboot.infrastructure.user.web.SystemLoginUser;
import com.agileboot.common.enums.common.OperationStatusEnum;
import com.agileboot.common.enums.common.RequestMethodEnum;
import com.agileboot.common.enums.BasicEnumUtil;
import com.agileboot.domain.system.log.db.SysOperationLogEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

/**
 * @author valarchie
 */
@Slf4j
public class OperationLogModel extends SysOperationLogEntity {

    public static final int MAX_DATA_LENGTH = 512;

    HttpServletRequest request = ServletHolderUtil.getRequest();

    public void fillOperatorInfo() {
        // 获取当前的用户
        String ip = ServletUtil.getClientIP(request);
        setOperatorIp(ip);
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        if (loginUser != null) {
            this.setUsername(loginUser.getUsername());
        }

        this.setOperationTime(DateUtil.date());
    }


    public void fillRequestInfo(final JoinPoint joinPoint, AccessLog accessLog, Object jsonResult) {
        this.setRequestUrl(request.getRequestURI());
        // 设置方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String methodFormat = StrUtil.format("{}.{}()", className, methodName);
        this.setCalledMethod(methodFormat);
        // 设置请求方式
        RequestMethodEnum requestMethodEnum = EnumUtil.fromString(RequestMethodEnum.class,
                request.getMethod());
        this.setRequestMethod(requestMethodEnum != null ? requestMethodEnum.getValue() : RequestMethodEnum.UNKNOWN.getValue());


        // 是否需要保存request，参数和值
        if (accessLog.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            recordRequestData(joinPoint);
        }
        // 是否需要保存response，参数和值
        if (accessLog.isSaveResponseData() && jsonResult != null) {
            this.setOperationResult(StrUtil.sub(JSONUtil.toJsonStr(jsonResult), 0, MAX_DATA_LENGTH));
        }
    }


    public void fillAccessLogInfo(AccessLog log) {
        // 设置action动作
        this.setBusinessType(log.businessType().ordinal());
        // 设置标题
        this.setRequestModule(log.title());
        // 设置操作人类别
        this.setOperatorType(log.operatorType().ordinal());
    }


    public void fillStatus(Exception e) {
        if (e != null) {
            this.setStatus(OperationStatusEnum.FAIL.getValue());
            this.setErrorStack(StrUtil.sub(e.getMessage(), 0, MAX_DATA_LENGTH));
        } else {
            this.setStatus(OperationStatusEnum.SUCCESS.getValue());
        }
    }


    /**
     * 获取请求的参数，放到log中
     *
     * @param joinPoint 方法切面
     */
    private void recordRequestData(JoinPoint joinPoint) {
        RequestMethodEnum requestMethodEnum = BasicEnumUtil.fromValue(RequestMethodEnum.class,
            this.getRequestMethod());

        if (requestMethodEnum == RequestMethodEnum.GET || requestMethodEnum == RequestMethodEnum.POST) {
            String params = argsArrayToString(joinPoint.getArgs());
            this.setOperationParam(StrUtil.sub(params, 0, MAX_DATA_LENGTH));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) request
                    .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            this.setOperationParam(StrUtil.sub(paramsMap.toString(), 0, MAX_DATA_LENGTH));
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null) {
            for (Object o : paramsArray) {
                if (o != null && !isCanNotBeParseToJson(o)) {
                    try {
                        Object jsonObj = JSONUtil.parseObj(o);
                        params.append(jsonObj).append(",");
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
    public boolean isCanNotBeParseToJson(final Object o) {
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
