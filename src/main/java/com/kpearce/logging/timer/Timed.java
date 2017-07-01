package com.kpearce.logging.timer;

import java.lang.annotation.*;

/**
 * Annotation that indicates a Class or Method should be timed.
 *
 * @see TimerAdvice
 * @see Timer
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Timed {

    /**
     * @return the Log4j log level at which timing information should be written
     */
    TimerLogLevel logLevel() default TimerLogLevel.DEBUG;

}
