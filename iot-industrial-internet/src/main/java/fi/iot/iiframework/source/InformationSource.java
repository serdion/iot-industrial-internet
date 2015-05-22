/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.dataobject.DataSourceObject;
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
    public DataSourceObject read() throws JAXBException, MalformedURLException;
    /**
     * Reads an DataSourceObject and writes it to database
     * @throws JAXBException
     * @throws MalformedURLException 
     */
    public void readAndWrite() throws JAXBException, MalformedURLException;
    /**
     * Set the frequency of reads and writes
     * @param seconds frequency in seconds
     */
    public void setReadFrequency(int seconds);
}
