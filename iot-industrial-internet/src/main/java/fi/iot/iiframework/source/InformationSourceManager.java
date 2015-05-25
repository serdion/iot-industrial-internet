/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * Creates and manages objects that represent external data sources
 */
@Component
public class InformationSourceManager {

    private List<InformationSource> sources;
    
    public InformationSourceManager() {
        this.sources = new ArrayList<>();
    }

    /**
     *
     * Creates and manages objects that represent external data sources
     *
     * @param config the configuration for this data source fetched from the
     * database
     */
    public void createSource(InformationSourceConfiguration config) {
        InformationSource source = new InformationSourceImpl(config);
        sources.add(source);
    }

    public void removeSource(String id) {
        
    }

    public List<InformationSource> getSources() {
        return sources;
    }

    
    
}
