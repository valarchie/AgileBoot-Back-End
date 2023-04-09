package com.agileboot.infrastructure.aspectj;

import com.agileboot.infrastructure.annotations.AccessLog;
import com.agileboot.infrastructure.thread.AsyncTaskFactory;
import com.agileboot.infrastructure.thread.ThreadPoolManager;
import com.agileboot.infrastructure.web.domain.operationLog.OperationLogModel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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

    protected void handleLog(final JoinPoint joinPoint, AccessLog accessLog, final Exception e, Object jsonResult) {
        try {
            OperationLogModel operationLog = new OperationLogModel();
            operationLog.fillOperatorInfo();
            operationLog.fillRequestInfo(joinPoint, accessLog, jsonResult);
            operationLog.fillStatus(e);
            operationLog.fillAccessLogInfo(accessLog);

            // 保存数据库
            ThreadPoolManager.execute(AsyncTaskFactory.recordOperationLog(operationLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("生成操作日志异常，异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }


}
