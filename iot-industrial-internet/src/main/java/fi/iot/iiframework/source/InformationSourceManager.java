/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.services.dataobject.DataSourceObjectService;
import fi.iot.iiframework.source.service.InformationSourceConfigurationService;
import fi.iot.iiframework.source.InformationSourceConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * Creates and manages objects that represent external data sources
 */
@Component
public class InformationSourceManager {

    private List<InformationSource> sources;
    
    @Autowired
    private DataSourceObjectService service;
    @Autowired
    private InformationSourceConfigurationService infService;

    
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
        InformationSource source = new InformationSourceImpl(config, service);
        sources.add(source);
        infService.save(config);
    }
    
    public List<InformationSourceConfiguration> getAllFromDB() {
        return infService.getAll();
    }

    public void removeSource(String id) {
        
    }

    public List<InformationSource> getSources() {
        return sources;
    }

    
    
}
