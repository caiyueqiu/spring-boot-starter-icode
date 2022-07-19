package com.icode.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author caiyq
 * @date 2022/7/15 21:37
 */
@Aspect
@Slf4j
public class LogAspect {

    @Around("execution(* com.icode.service.*.*(..))")
    public Object businessService(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        log.info("execute method: {}", method.getName());
        return joinPoint.proceed();
    }
}
