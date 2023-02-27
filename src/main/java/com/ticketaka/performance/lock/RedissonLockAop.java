package com.ticketaka.performance.lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class RedissonLockAop {
    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;

    @Around("@annotation(com.ticketaka.performance.lock.RedissonLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);

        String key = this.createKey(signature.getParameterNames(), joinPoint.getArgs(), redissonLock.key());
        RLock rLock = redissonClient.getLock(key);

        try {
            boolean available = rLock.tryLock(redissonLock.waitTime(), redissonLock.leaseTime(), redissonLock.timeUnit());
            if (!available) {
                return false;
            }

            log.info("Redisson lock key : {}" , key);
            return aopForTransaction.proceed(joinPoint);
        } catch (RedisException e) {
            e.printStackTrace();
        } finally {
            if (rLock.isHeldByCurrentThread()) {
                log.info("Redisson unlocked");
                rLock.unlock();
            }
//            rLock.unlockAsync(Thread.currentThread().getId());
        }

        return true;
    }

    private String createKey(String[] parameterNames, Object[] args, String key) {
        String resultKey = key;

        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals(key)) {
                resultKey += args[i];
                break;
            }
        }
        return resultKey;
    }
}
