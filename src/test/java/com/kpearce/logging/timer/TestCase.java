package com.kpearce.logging.timer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-context.xml")
public class TestCase {

    @Autowired
    private NotTimedClass notTimedClass;

    @Autowired
    private NotTimedMethods notTimedMethods;

    @Autowired
    private TimedClass timedClass;

    @Autowired
    private TimedMethods timedMethods;

    @Test
    public void testTimer() throws Exception {
        timedClass.shortMethod();
        timedClass.longMethod();

        notTimedClass.shortMethod();
        notTimedClass.longMethod();

        timedMethods.shortMethod();
        timedMethods.longMethod();

        notTimedMethods.shortMethod();
        notTimedMethods.longMethod();
    }

}
