/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.datasourcereaders.InformationSourceReader;
import fi.iot.iiframework.datasourcereaders.XMLReader;
import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.services.domain.InformationSourceObjectService;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;

public final class InformationSourceImpl implements InformationSource {

    /**
     * Configuration for this source.
     */
    private InformationSourceConfiguration config;
    /**
     * Reader used to read the server information.
     */
    private InformationSourceReader reader;
    /**
     * Scheduler that schedules the read operation based on config.
     */
    private final ReadScheduler scheduler;
    /**
     * Service for database transactions.
     */
    @Autowired
    private final InformationSourceObjectService service;

    public InformationSourceImpl(InformationSourceConfiguration config, InformationSourceObjectService service) {
        this.config = config;
        this.service = service;
        this.scheduler = new ReadSchedulerImpl();
        initReader();
        schedule();
    }

    /**
     * Initialize the type of reader this class will use.
     */
    private void initReader() {
        switch (config.type) {
            case XML:
                this.reader = new XMLReader(config.url);
                break;
            default:
                throw new AssertionError(config.type.name());
        }

    }

    /**
     * Initialize scheduler.
     */
    private void schedule() {
        scheduler.cancel();
        if (config.active && config.readFrequency > 0) {
            scheduler.schedule(config.readFrequency, this::readAndWrite);
        }
    }

    /**
     * Ensure that scheduler and reader match the configuration.
     */
    private void update() {
        initReader();
        schedule();
    }

    @Override
    public boolean readAndWrite() {
        InformationSourceObject isobj = read();
        if (isobj == null)
            return false;
        isobj.setInformationSource(config);
        service.save(isobj);
        return true;
    }

    @Override
    public InformationSourceObject read() {
        InformationSourceObject isobj = null;
        try {
            isobj = reader.read();
        } catch (JAXBException ex) {
            ErrorLogger.log(ErrorType.PARSE_ERROR, ErrorSeverity.LOW, "XML returned could not be read for source: " + config.url);
        } catch (IOException ex) {
            ErrorLogger.log(ErrorType.PARSE_ERROR, ErrorSeverity.LOW, "IOException reeading source: " + config.url);
        }
        return isobj;
    }

    @Override
    public InformationSourceConfiguration getConfig() {
        return config;
    }

    @Override
    public void setConfig(InformationSourceConfiguration config) {
        this.config = config;
        update();
    }

    @Override
    public void close() {
        scheduler.cancel();
    }

}
