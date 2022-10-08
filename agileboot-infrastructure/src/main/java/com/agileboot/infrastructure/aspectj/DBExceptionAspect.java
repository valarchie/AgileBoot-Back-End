package com.agileboot.infrastructure.aspectj;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @author valarchie
 */
@Aspect
@Component
@Slf4j
public class DBExceptionAspect {


    @Pointcut("within(com.agileboot.orm..*)")
    public void dbException() {
    }

    @Around("dbException()")
    public Object aroundDbException(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Exception e) {
//            log.error("catch the DB EXCEPTION, CLASS NAME : {} ; REQUEST：{} ;  EXCEPTION : {}",
//                joinPoint.getSignature().toShortString(),
//                joinPoint.getArgs(),
//                e.getMessage());
            throw new ApiException(e, ErrorCode.Internal.DB_INTERNAL_ERROR, e.getMessage());
        }
        return proceed;
    }


}
