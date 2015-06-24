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

/**
 * Parser to parse Sensors and their Readouts from given location.
 */
public interface Parser {
    
    /**
     * Parse information from given location.
     * @param location
     * @return list of sensors containing their readouts.
     */
    public List<Sensor> parse(String location);
    
    /**
     * Parse information from given location.
     * @param url
     * @return list of sensors containing their readouts.
     * @throws java.io.IOException
     */
    public List<Sensor> parse(URL url) throws IOException;
}
