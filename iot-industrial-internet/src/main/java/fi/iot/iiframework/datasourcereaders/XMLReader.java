/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.datasourcereaders;

import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.xmltodataobject.XmlToObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

public class XMLReader implements InformationSourceReader {

    private String URI;

    public XMLReader(String URI) {
        this.URI = URI;
    }

    @Override
    public InformationSourceObject read() throws JAXBException, MalformedURLException, IOException {
        return XmlToObject.convertXml(URI);
    }

}
