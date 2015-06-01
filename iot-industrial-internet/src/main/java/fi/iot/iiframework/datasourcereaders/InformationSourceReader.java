/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.datasourcereaders;

import fi.iot.iiframework.dataobject.DataSourceObject;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.bind.JAXBException;

/**
 * Defines reader for a DataSource.
 * 
 */
public interface InformationSourceReader {
    public DataSourceObject read() throws JAXBException, MalformedURLException, IOException;
}
