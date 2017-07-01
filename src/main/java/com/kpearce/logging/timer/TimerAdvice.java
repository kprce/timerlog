package com.kpearce.logging.timer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class TimerAdvice {

    /**
     * Calls {@link Timer#begin(Level, String)} and {@link Timer#end(Level, String)} around public
     * methods annotated as {@link Timed} at the class or method level. Excludes classes and methods
     * specifically annotated as {@link NotTimed}.
     *
     * @param pjp a {@link ProceedingJoinPoint}
     * @return the return value of the advised method
     * @throws Throwable if the advised method throws a Throwable
     */
    @Around("execution(public * *(..)) && " +
            "(@target(com.kpearce.logging.timer.Timed) || @annotation(com.kpearce.logging.timer.Timed)) && " +
            "!(@target(com.kpearce.logging.timer.NotTimed) || @annotation(com.kpearce.logging.timer.NotTimed))")
    public Object timedMethod(ProceedingJoinPoint pjp) throws Throwable {

        // find the log level from the Timed annotation
        Level logLevel;
        Timed timedAnnotation;

        // look for a Timed annotation on the method
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        timedAnnotation = targetMethod.getAnnotation(Timed.class);

        // if not found look at the class
        if (timedAnnotation == null) {
            timedAnnotation = pjp.getTarget().getClass().getAnnotation(Timed.class);
        }

        logLevel = timedAnnotation.logLevel().getLevel();

        // log the timing information
        String signature = pjp.getSignature().toLongString();
        Timer.begin(logLevel, signature);
        Object result = null;
        try {
            result = pjp.proceed();
        } finally {
            Timer.end(logLevel, signature);
        }
        return result;
    }

}
