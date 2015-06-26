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

/**
 * Responsible for providing the methods to create new timer tasks or to cancel
 * old timer tasks for added or edited sources
 */
public class ReadSchedulerImpl implements ReadScheduler {

    Timer timer;

    /**
     * Schedules the source to be read and its information to be written to the
     * database at the time(s) specified by the user
     *
     * @param source the information source in question
     * @param method the method for reading the source and writing its
     * information to the database
     */
    @Override
    public void schedule(InformationSource source, Runnable method) {
        if (source.isActive() && source.getStartDate() != null) {
            switch (source.getReadInterval()) {
                case NEVER:
                    scheduleOnlyOnce(source.getStartDate(), method);
                    break;
                case HOURLY:
                    scheduleAtSpecificInterval(TimeUnit.HOURS.toMillis(1), source.getStartDate(), source.getEndDate(), method);
                    break;
                case DAILY:
                    scheduleAtSpecificInterval(TimeUnit.DAYS.toMillis(1), source.getStartDate(), source.getEndDate(), method);
                    break;
                case WEEKLY:
                    scheduleAtSpecificInterval(TimeUnit.DAYS.toMillis(7), source.getStartDate(), source.getEndDate(), method);
                    break;
                case MONTHLY:
                    scheduleAtSpecificInterval(TimeUnit.DAYS.toMillis(30), source.getStartDate(), source.getEndDate(), method);
                    break;
                case OTHER:
                    scheduleAtSpecificInterval(source.getOtherInterval() * 1000, source.getStartDate(), source.getEndDate(), method);
                    break;
            }
        }

    }

    /**
     * Schedules the source to be read and its information to be written to the
     * database starting from a specific date and time, then again in specific
     * intervals, possibly ending at a specified date and time
     *
     * @param interval the interval between reading and writing
     * @param startDate the starting date and time for the source to be read and
     * written
     * @param endDate the possible ending date and time for the source to be
     * read and written
     * @param method the method for reading the source and writing its
     * information to the database
     */
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

    /**
     * Schedules the source to be read and its information to be written to the
     * database only once at a specific time
     *
     * @param startDate the date and time for the source to be read and written
     * @param method the method for reading the source and writing its
     * information to the database
     */
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

    /**
     * Cancels the timer task for a specific scheduler
     */
    @Override
    public void cancel() {
        if (timer != null) {
            timer.cancel();
        }
        timer = null;
    }
}
