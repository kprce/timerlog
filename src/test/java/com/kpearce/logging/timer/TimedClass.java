package com.kpearce.logging.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Timed
public class TimedClass {

    @Autowired
    private Methods methods;

    public void shortMethod() throws InterruptedException {
        methods.shortMethod();
    }

    public void longMethod() throws InterruptedException {
        methods.longMethod();
    }

}
