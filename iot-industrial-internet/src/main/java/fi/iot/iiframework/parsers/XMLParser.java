/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.parsers;

import fi.iot.iiframework.domain.Sensor;
import java.io.IOException;
import java.net.URL;
import java.util.List;


public class XMLParser implements Parser{

    @Override
    public List<Sensor> parse(String location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sensor> parse(URL url) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
