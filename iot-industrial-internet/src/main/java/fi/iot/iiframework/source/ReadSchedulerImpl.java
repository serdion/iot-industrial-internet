/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSource;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ReadSchedulerImpl implements ReadScheduler {

    Timer timer;

    @Override
    public void schedule(InformationSource source, Runnable method) {
        if (source.isActive() && source.getStartDate() != null) {
            switch (source.getReadInterval()) {
                case NEVER:
                    scheduleOnlyOnce(source.getStartDate(), method);
                    break;
                case HOURLY:
                    scheduleAtSpecificInterval(TimeUnit.HOURS.toSeconds(1), source.getStartDate(), source.getEndDate(), method);
                    break;
                case DAILY:
                    scheduleAtSpecificInterval(TimeUnit.DAYS.toSeconds(1), source.getStartDate(), source.getEndDate(), method);
                    break;
                case WEEKLY:
                    scheduleAtSpecificInterval(TimeUnit.DAYS.toSeconds(7), source.getStartDate(), source.getEndDate(), method);
                    break;
                case MONTHLY:
                    scheduleAtSpecificInterval(TimeUnit.DAYS.toSeconds(30), source.getStartDate(), source.getEndDate(), method);
                    break;
                case OTHER:
                    scheduleAtSpecificInterval(source.getOtherInterval() * 1000, source.getStartDate(), source.getEndDate(), method);
                    break;
            }
        }

    }

    @Override
    public void scheduleAtSpecificInterval(final long interval, final Date startDate, final Date endDate, final Runnable method) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (endDate != null && new Date().after(endDate)) {
                    timer.cancel();
                }
                method.run();
            }

        }, startDate, interval);

    }

    @Override
    public void scheduleOnlyOnce(final Date startDate, final Runnable method) {
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                method.run();
            }

        }, startDate);

    }

    @Override
    public void cancel() {
        if (timer != null) {
            timer.cancel();
        }
        timer = null;
    }

}