/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

/**
 *
 * An object representing an external data source
 */
public class InformationSource {
    
    private InformationSourceConfiguration config;
    
    public InformationSource (InformationSourceConfiguration config) {
        this.config = config;
    }
    
}
