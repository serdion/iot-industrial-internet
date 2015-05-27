/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.datasourcereaders.InformationSourceReader;
import fi.iot.iiframework.datasourcereaders.XMLReader;
import java.io.Serializable;
import fi.iot.iiframework.services.DataSourceObjectService;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;

public class InformationSourceImpl implements InformationSource {

    /**
     * Configuration
     */
    private int id;

    private InformationSourceConfiguration config;

    /**
     * Reader used to read the server information
     */
    private InformationSourceReader reader;
    /**
     * Scheduler that schedules the read operation based on config.
     */

    private Timer scheduler;
    @Autowired
    private DataSourceObjectService service;

    public InformationSourceImpl(InformationSourceConfiguration config, DataSourceObjectService service) {
        this.config = config;
        this.service = service;
        createReader();
        createOrUpdateScheduler();
    }

    private void createReader() {
        switch (config.type) {
            case XML:
                this.reader = new XMLReader(config.url);
                break;
            default:
                throw new AssertionError(config.type.name());
        }

    }

    private void createOrUpdateScheduler() {
        if (scheduler != null) {
            scheduler.cancel();
            scheduler = null;
        }
        if (config.readFrequency == 0) {
            return;
        }
        scheduler = new Timer();

        scheduler.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                try {
                    readAndWrite();
                } catch (JAXBException | MalformedURLException ex) {
                    Logger.getLogger(InformationSource.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }, 0, config.readFrequency);
    }

    @Override
    public void readAndWrite() throws JAXBException, MalformedURLException {
        DataSourceObject dso = read();
        service.add(dso);
    }

    @Override
    public DataSourceObject read() throws JAXBException, MalformedURLException {
        DataSourceObject dobj = reader.read();
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

    public void setId(String id) {
        config.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public String getName() {
        return config.name;
    }

    public void setName(String name) {
        config.name = name;
    }

    public InformationSourceType getType() {
        return config.type;
    }

    public void setType(InformationSourceType type) {
        config.type = type;
    }

    public String getUrl() {
        return config.url;
    }

    public void setUrl(String url) {
        config.url = url;
    }

    @Override
    public void setReadFrequency(int readFrequency) {
        config.readFrequency = readFrequency;
        createOrUpdateScheduler();
    }

    @Override
    public int getReadFrequency() {
        return config.getReadFrequency();
    }

}
