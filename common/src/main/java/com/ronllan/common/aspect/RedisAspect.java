package com.ronllan.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ronllan.common.exception.ErrorCode;
import com.ronllan.common.exception.DefineException;

/**
 * Redis切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
@Aspect
@Component
public class RedisAspect {
    /**
     * 是否开启redis缓存  true开启   false关闭
     */
    @Value("${system.redis.open}")
    private boolean open;

    @Around("execution(* com.ronllan.common.redis.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if (open) {
            try {
                result = point.proceed();
            } catch (Exception e) {
                log.error("redis error", e);
                throw new DefineException(ErrorCode.REDIS_ERROR);
            }
        }
        return result;
    }
}
