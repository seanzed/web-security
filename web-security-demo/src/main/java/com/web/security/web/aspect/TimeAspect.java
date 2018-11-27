package com.web.security.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 切面Aspect
 * 可以得到方法名称和方法参数， 不可以得到HttpServletRequest和HttpServletResponse对象。
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.web.security.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+arg);
        }

        long start = System.currentTimeMillis();

        Object object = pjp.proceed();

        System.out.println("time aspect 耗时:"+ (System.currentTimeMillis() - start));

        System.out.println("time aspect end");

        return object;
    }
}
