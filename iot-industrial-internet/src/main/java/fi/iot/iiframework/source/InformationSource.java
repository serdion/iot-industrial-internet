/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.datasourcereaders.InformationSourceReader;

/**
 *
 * An object representing an external data source
 */
public class InformationSource {

    private InformationSourceConfiguration config;
    private InformationSourceReader reader;

    public InformationSource(InformationSourceConfiguration config) {
        this.config = config;
    }

    public String getId() {
        return config.getId();
    }
    
    public void createReader() {
        
    }
    
    
}
