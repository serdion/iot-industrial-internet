/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSource;
import java.util.Date;

/**
 * Handler for JavaTimer class that can schedule a method to run at specific intervals.
 */
public interface ReadScheduler {
    
    public void schedule(InformationSource source, Runnable method);

    public void scheduleAtSpecificInterval(final long interval, final Date startDate, final Date endDate, final Runnable runnable);

    public void scheduleOnlyOnce(final Date startDate, final Runnable runnable);

    public void cancel();
}
