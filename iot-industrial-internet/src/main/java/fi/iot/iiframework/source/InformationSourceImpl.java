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
import java.net.MalformedURLException;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;

public final class InformationSourceImpl implements InformationSource {

    /**
     * Config for this source.
     */
    private InformationSourceConfiguration config;

    /**
     * Reader used to read the server information.
     */
    private InformationSourceReader reader;
    /**
     * Scheduler that schedules the read operation based on config.
     */
    private ReadScheduler scheduler;
    /**
     * Service for database transactions.
     */
    @Autowired
    private InformationSourceObjectService service;

    public InformationSourceImpl(InformationSourceConfiguration config, InformationSourceObjectService service) {
        this.config = config;
        this.service = service;
        this.scheduler = new ReadSchedulerImpl();
        initReader();
        schedule();
    }

    private void initReader() {
        switch (config.type) {
            case XML:
                this.reader = new XMLReader(config.url);
                break;
            default:
                throw new AssertionError(config.type.name());
        }

    }
    
    public void schedule() {
        scheduler.cancel();
        if (config.active && config.readFrequency > 0) {
            scheduler.schedule(config.readFrequency, this::readAndWrite);
        }
    }
    
    @Override
    public void readAndWrite() {
        InformationSourceObject dso = null;
        try {
            dso = read();
        } catch (JAXBException ex) {
            ErrorLogger.log(ErrorType.PARSE_ERROR, ErrorSeverity.LOW, "XML returned could not be read for source: " + config.url, null);
        } catch (IOException ex) {
            ErrorLogger.log(ErrorType.PARSE_ERROR, ErrorSeverity.LOW, "IOException reeading source: " + config.url, null);
        }
        service.save(dso);
    }

    @Override
    public InformationSourceObject read() throws JAXBException, MalformedURLException, IOException {
        InformationSourceObject dobj = reader.read();
        return dobj;
    }

    @Override
    public InformationSourceConfiguration getConfig() {
        return config;
    }

    @Override
    public void setConfig(InformationSourceConfiguration config) {
        this.config = config;
    }
}
