/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.IntervalType;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.readers.InformationSourceReader;
import fi.iot.iiframework.readers.SparkfunDataReader;
import fi.iot.iiframework.readers.XMLReader;
import java.util.List;
import org.springframework.scheduling.annotation.Async;

public final class InformationSourceHandlerImpl implements InformationSourceHandler {

    /**
     * Configuration for the operations in this class.
     */
    private InformationSource source;
    /**
     * Reader used to read the server information.
     */
    private InformationSourceReader reader;
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
        initReader();
        schedule();
    }

    /**
     * Initialize the type of reader this class will use.
     */
    private void initReader() {
        switch (source.getType()) {
            case XML:
                this.reader = new XMLReader();
                break;
            case JSON:
                this.reader = new SparkfunDataReader();
                break;
            default:
                throw new AssertionError(source.getType().name());
        }

    }

    /**
     * Initialize scheduler.
     */
    private void schedule() {
        scheduler.cancel();
//        if (source.isActive() && source.getReadFrequency() > 0) {
//            scheduler.schedule(source.getReadFrequency(), this::readAndWrite);
//            System.out.println("Frequently runnable task created");
//        }

        if (source.isActive() && source.getStartDate() != null) {
            switch (source.getReadInterval()) {
                case NEVER:
                    scheduler.scheduleOnlyOnce(source.getStartDate(), this::readAndWrite);
                    System.out.println("Once runnable task created");
                    break;
                case DAILY:
                    scheduler.scheduleAtSpecificInterval(86400000, source.getStartDate(), source.getEndDate(), this::readAndWrite);
                    System.out.println("Daily task created");
                    break;
                case WEEKLY:
                    scheduler.scheduleAtSpecificInterval(604800000, source.getStartDate(), source.getEndDate(), this::readAndWrite);
                    System.out.println("Weekly task created");
                    break;
                case MONTHLY:
                    scheduler.scheduleAtSpecificInterval(2419200000L, source.getStartDate(), source.getEndDate(), this::readAndWrite);
                    System.out.println("Monthly task created");
                    break;
                case OTHER:
                    scheduler.scheduleAtSpecificInterval(source.getOtherInterval(), source.getStartDate(), source.getEndDate(), this::readAndWrite);
                    System.out.println("Other task created");
                    break;
            }
        }

        // back end testing for setting a specific date and interval to a timer
    }

    /**
     * Ensure that scheduler and reader match the configuration.
     */
    private void update() {
        initReader();
        schedule();
    }

    @Async
    @Override
    public void readAndWrite() {
        List<Sensor> sensors = read();
        if (sensors == null) {
            return;
        }
        persistence.updateSourceWithSensors(source, sensors);
    }

    @Override
    public List<Sensor> read() {
        List<Sensor> sensor = reader.read(source.getUrl());
        return sensor;
    }

    @Override
    public InformationSource getConfig() {
        return source;
    }

    @Override
    public void setConfig(InformationSource config) {
        this.source = config;
        update();
    }

    @Override
    public void close() {
        scheduler.cancel();
    }

    @Override
    public void setReader(InformationSourceReader reader) {
        this.reader = reader;
    }

    @Override
    public void setScheduler(ReadScheduler scheduler) {
        this.scheduler = scheduler;
    }

}
