package com.kpearce.logging.timer;

import org.slf4j.event.Level;

public enum TimerLogLevel {
    DEBUG(Level.DEBUG),
    INFO(Level.INFO)
    ;

    private Level logLevel;

    TimerLogLevel(Level logLevel) {
        this.logLevel = logLevel;
    }

    public Level getLevel() {
        return logLevel;
    }

}
