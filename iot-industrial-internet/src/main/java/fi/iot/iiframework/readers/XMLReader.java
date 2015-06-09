/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.readers;

import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.parsers.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;

public class XMLReader implements InformationSourceReader {
    
    @Autowired
    private XmlParser parser;

    @Override
    public InformationSourceObject read(String location) {
        return parser.parse(location);
    }

}
