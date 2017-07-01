package com.kpearce.logging.timer;

import java.lang.annotation.*;

/**
 * Annotation that indicates a Class or Method should NOT be timed.
 *
 * @see TimerAdvice
 * @see Timer
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface NotTimed {
}
