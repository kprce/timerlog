package com.kpearce.logging.timer;

import org.springframework.stereotype.Component;

@Component
@Timed
public class Methods {

    private final Object mutex = new Object();

    public void shortMethod() throws InterruptedException {
        synchronized (mutex) {
            mutex.wait(50);
        }
    }

    public void longMethod() throws InterruptedException {
        synchronized (mutex) {
            mutex.wait(100);
        }
    }

}
