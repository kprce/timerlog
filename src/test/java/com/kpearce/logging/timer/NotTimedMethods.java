package com.kpearce.logging.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Timed
public class NotTimedMethods {

    @Autowired
    private Methods methods;

    @NotTimed
    public void shortMethod() throws InterruptedException {
        methods.shortMethod();
    }

    @NotTimed
    public void longMethod() throws InterruptedException {
        methods.longMethod();
    }

}
