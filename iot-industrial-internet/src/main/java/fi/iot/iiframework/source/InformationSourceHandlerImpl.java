/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.parsers.ParserContainer;
import fi.iot.iiframework.parsers.SparkfunDataParser;
import java.util.List;

public final class InformationSourceHandlerImpl implements InformationSourceHandler {

    /**
     * Configuration for the operations in this class.
     */
    private InformationSource source;
    /**
     * Scheduler that schedules the read operation based on configuration.
     */
    private ReadScheduler scheduler;
    /**
     * Service for database transactions.
     */
    private final InformationSourcePersistence persistence;

    public InformationSourceHandlerImpl(InformationSource source, InformationSourcePersistence persistence) {
        this.source = source;
        this.persistence = persistence;
        this.scheduler = new ReadSchedulerImpl();
        schedule();
    }

    /**
     * Initialize scheduler.
     */
    private void schedule() {
        scheduler.cancel();

        if (source.isActive() && source.getStartDate() != null) {
            switch (source.getReadInterval()) {
                case NEVER:
                    scheduler.scheduleOnlyOnce(source.getStartDate(), this::readAndWrite);
                    break;
                case HOURLY:
                    scheduler.scheduleAtSpecificInterval(3600000, source.getStartDate(), source.getEndDate(), this::readAndWrite);
                    break;
                case DAILY:
                    scheduler.scheduleAtSpecificInterval(86400000, source.getStartDate(), source.getEndDate(), this::readAndWrite);
                    break;
                case WEEKLY:
                    scheduler.scheduleAtSpecificInterval(604800000, source.getStartDate(), source.getEndDate(), this::readAndWrite);
                    break;
                case MONTHLY:
                    scheduler.scheduleAtSpecificInterval(2419200000L, source.getStartDate(), source.getEndDate(), this::readAndWrite);
                    break;
                case OTHER:
                    scheduler.scheduleAtSpecificInterval(source.getOtherInterval() * 1000, source.getStartDate(), source.getEndDate(), this::readAndWrite);
                    break;
            }
        }
    }

    /**
     * Ensure that scheduler and reader match the configuration.
     */
    private void update() {
        schedule();
    }

    @Override
    public void readAndWrite() {
        source.setLastReadout(System.currentTimeMillis());
        List<Sensor> sensors = read();
        if (sensors == null) {
            return;
        }
        persistence.updateSource(source);
        persistence.writeReadoutsToSource(source, sensors);
    }

    @Override
    public List<Sensor> read() {
        List<Sensor> sensor = ParserContainer.getParsers()
                .get(source.getType())
                .parse(source.getUrl());
        return sensor;
    }

    @Override
    public InformationSource getSource() {
        return source;
    }

    @Override
    public void setSource(InformationSource config) {
        this.source = config;
        update();
    }

    @Override
    public void close() {
        scheduler.cancel();
    }

    @Override
    public void setScheduler(ReadScheduler scheduler) {
        this.scheduler = scheduler;
    }

}
