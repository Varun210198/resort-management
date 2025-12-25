package com.example.vvs.InventoryService.Aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LocationServiceAspect {

    // ✅ Runs BEFORE every method in ProductServiceImpl
    @Before("execution(* com.example.vvs.InventoryService.Services.InventoryService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("InventoryService."+methodName+" method started");
    }

    // ✅ Runs AFTER successful execution
    @AfterReturning("execution(* com.example.vvs.InventoryService.Services.InventoryService.*(..))")
    public void logAfterSuccess(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("InventoryService."+methodName+" completed successfully");
    }

    // ✅ Runs if exception occurs
    @AfterThrowing(
            pointcut = "execution(* com.example.vvs.InventoryService.Services.InventoryService.*(..))",
            throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Exception occurred: " + ex.getMessage());
    }

    // ✅ Performance timing (real production use)
    @Around("execution(* com.example.vvs.InventoryService.Services.InventoryService.*(..))")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();   // calls your service method
        long end = System.currentTimeMillis();

        System.out.println(
                joinPoint.getSignature().getName() +
                        " executed in " + (end - start) + " ms"
        );

        return result;
    }
}

