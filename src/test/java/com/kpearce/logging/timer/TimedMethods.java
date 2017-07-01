package com.kpearce.logging.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimedMethods {

    @Autowired
    private Methods methods;

    @Timed
    public void shortMethod() throws InterruptedException {
        methods.shortMethod();
    }

    @Timed
    public void longMethod() throws InterruptedException {
        methods.longMethod();
    }

}
