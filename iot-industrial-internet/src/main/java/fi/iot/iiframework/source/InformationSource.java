/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSourceObject;
import java.io.IOException;
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
     * @throws JAXBException
     * @throws MalformedURLException
     */
    public InformationSourceObject read() throws JAXBException, MalformedURLException, IOException;

    /**
     * Reads an DataSourceObject and writes it to database
     *
     * @throws JAXBException
     * @throws MalformedURLException
     */
    public void readAndWrite() throws JAXBException, MalformedURLException, IOException;

    public void setConfig(InformationSourceConfiguration config);

    public InformationSourceConfiguration getConfig();
}
