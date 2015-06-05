/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author atte
 */
public class ReadSchedulerImpl implements ReadScheduler {

    Timer timer;
    
    @Override
    public void schedule(final int interval, final Runnable r) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {
                System.out.println("task run");
                r.run();
            }

        }, 0, interval);

    }

    @Override
    public void cancel() {
        if (timer != null) {
            timer.cancel();
        }
        timer = null;
    }

}
