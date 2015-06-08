/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSourceObject;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBException;

/**
 *
 * An object representing an external data source
 */
public interface InformationSource {

    /**
     * Read a source from the reader
     *
     * @return DataSourceObject read from URL and parsed
     */
    public InformationSourceObject read();

    /**
     * Reads an DataSourceObject and writes it to database.
     *
     * @return true, if succesful, false if unsuccesful
     */
    public boolean readAndWrite();

    /**
     * Set configuration for this instance and update the readers and
     * schedulers, if they have changed.
     *
     * @param config
     */
    public void setConfig(InformationSourceConfiguration config);

    /**
     * Get the configuration.
     * @return 
     */
    public InformationSourceConfiguration getConfig();
    
    /**
     * Stop all read-operations.
     */
    public void close();
}
