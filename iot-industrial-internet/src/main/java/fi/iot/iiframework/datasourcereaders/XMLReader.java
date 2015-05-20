/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.datasourcereaders;

import fi.iot.iiframework.dataobject.DataObject;
import fi.iot.iiframework.xmltodataobject.XmlToObject;

public class XMLReader implements InformationSourceReader{
    private String URI;

    public XMLReader(String URI) {
        this.URI = URI;
    }
    
    @Override
    public DataObject read() {
        return XmlToObject.convertXml(URI);
    }
    
}
