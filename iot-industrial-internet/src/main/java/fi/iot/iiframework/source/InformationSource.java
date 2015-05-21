/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.dataobject.DataObject;
import fi.iot.iiframework.datasourcereaders.InformationSourceReader;
import fi.iot.iiframework.datasourcereaders.XMLReader;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBException;

/**
 *
 * An object representing an external data source
 */
public class InformationSource {

    private InformationSourceConfiguration config;
    private InformationSourceReader reader;

    public InformationSource(InformationSourceConfiguration config) {
        this.config = config;
        createReader();
    }

    public String getId() {
        return config.id;
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
    
    public void readAndWrite() throws JAXBException, MalformedURLException{
        DataObject dobj = reader.read();
        //TODO: db.write dobj
    }
    
    //TODO: Timers.
}
