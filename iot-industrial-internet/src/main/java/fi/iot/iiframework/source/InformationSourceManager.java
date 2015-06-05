/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.services.domain.InformationSourceObjectService;
import fi.iot.iiframework.services.source.InformationSourceConfigurationService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * Creates and manages objects that represent external data sources
 */
@Component
public class InformationSourceManager {

    private Map<String, InformationSource> sources;

    @Autowired
    private InformationSourceObjectService service;
    @Autowired
    private InformationSourceConfigurationService configService;

    public InformationSourceManager() {
        this.sources = new HashMap<>();
    }

    /**
     *
     * Creates an object that represents an external data source
     *
     * @param config the new configuration for this data source
     */
    public void createSource(InformationSourceConfiguration config) {
        InformationSource source = new InformationSourceImpl(config, service);
        sources.put(config.id, source);
        configService.save(config);
    }

    /**
     *
     * Deletes an object that represents an external data source
     *
     * @param id the id of the data source representation to be deleted
     */
    public void removeSource(String id) {
        sources.remove(id);
        configService.delete(configService.get(id));
    }

    /**
     *
     * Updates the configuration information of an object that represents an external data source
     *
     * @param config the new configuration that will replace the previous one
     */
    public void updateSource(InformationSourceConfiguration config) { 
        sources.get(config.id).setConfig(config);
        configService.save(config);
    }

    public Map<String, InformationSource> getSources() {
        return sources;
    }

    public InformationSourceConfiguration getSourceConfigFromDB(String id) {
        return configService.get(id);
    }

    public List<InformationSourceConfiguration> getAllSourceConfigsFromDB() {
        return configService.getAll();
    }

}
