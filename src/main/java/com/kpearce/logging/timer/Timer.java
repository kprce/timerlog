package com.kpearce.logging.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.Stack;

public class Timer {

    private static Logger timer = LoggerFactory.getLogger("timer");
    private static ThreadLocal<Stack<Long>> methodBeginTimeStack = new ThreadLocal<>();

//    static {
//        timer.setAdditivity(false);
//    }

    public static void begin(String methodname) {
        begin(Level.DEBUG, methodname);
    }

    @SuppressWarnings("unchecked")
    public static void begin(Level level, String methodname) {
        if (!isLogLevelEnabled(level)) return;
        Stack<Long> times = methodBeginTimeStack.get();
        if (times == null) {
            times = new Stack<>();
            methodBeginTimeStack.set(times);
        }
        times.push(System.currentTimeMillis());
        log(level, methodname + " | begin");
    }

    public static void end(String methodname) {
        end(Level.DEBUG, methodname);
    }

    @SuppressWarnings("unchecked")
    public static void end(Level level, String methodname) {
        if (!isLogLevelEnabled(level)) return;
        Stack<Long> times = methodBeginTimeStack.get();
        Long elapsed = null;
        if (times != null) {
            try {
                elapsed = times.pop();
            } catch (Exception e) {
                //
            }
        }
        elapsed = (elapsed == null) ? 0L : elapsed;
        log(level, methodname + " | end | elapsed " +
                (System.currentTimeMillis() - elapsed) + " ms");
    }

    public static boolean isLogLevelEnabled(Level level) {
        if (level.equals(Level.DEBUG)) {
            return timer.isDebugEnabled();
        } else if (level.equals(Level.INFO)) {
            return timer.isInfoEnabled();
        }

        return false;
    }

    private static void log(Level level, String message) {
        if (level.equals(Level.DEBUG)) {
            timer.debug(message);
        } else if (level.equals(Level.INFO)) {
            timer.info(message);
        }
    }

    public static void clearContext() {
        methodBeginTimeStack.remove();
    }

}
