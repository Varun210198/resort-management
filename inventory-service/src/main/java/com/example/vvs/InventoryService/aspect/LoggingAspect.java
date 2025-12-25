package com.example.vvs.InventoryService.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.vvs..*Service.*(..))")
    public Object logServiceMethods(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();

        log.info("START {} with args {}", pjp.getSignature(), Arrays.toString(pjp.getArgs()));

        try {
            Object result = pjp.proceed();
            long time = System.currentTimeMillis() - start;
            log.info("END {} in {} ms", pjp.getSignature(), time);
            return result;

        } catch (Exception ex) {
            log.error("ERROR in {}: {}", pjp.getSignature(), ex.getMessage());
            throw ex;
        }
    }
}

