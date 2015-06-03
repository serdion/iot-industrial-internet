/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.services.domain.InformationSourceObjectService;
import fi.iot.iiframework.source.service.InformationSourceConfigurationService;
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
    private InformationSourceObjectService service;
    @Autowired
    private InformationSourceConfigurationService configService;

    public InformationSourceManager() {
        this.sources = new ArrayList<>();
    }

    /**
     *
     * Creates an object that represents an external data source
     *
     * @param config the new configuration for this data source
     */
    public void createSource(InformationSourceConfiguration config) {
        InformationSource source = new InformationSourceImpl(config, service);
        sources.add(source);
        configService.save(config);
    }

    /**
     *
     * Deletes an object that represents an external data source
     *
     * @param id the id of the data source representation to be deleted
     */
    public void removeSource(String id) {
        sources.removeIf(p -> id.equals(p.getId()));
        configService.delete(configService.get(id));
    }

    /**
     *
     * Updates the configuration information of an object that represents an external data source
     *
     * @param id the id of the data source representation to be updated
     * @param config the new configuration that will replace the previous one
     */
    public void updateSource(String id, InformationSourceConfiguration config) {
        sources.removeIf(p -> id.equals(p.getId()));
        sources.add(new InformationSourceImpl(config, service));
        InformationSourceConfiguration newConfig = config;
        newConfig.setId(id);
        createSource(newConfig);
    }

    public List<InformationSource> getSources() {
        return sources;
    }

    public InformationSourceConfiguration getSourceConfigFromDB(String id) {
        return configService.get(id);
    }

    public List<InformationSourceConfiguration> getAllSourceConfigsFromDB() {
        return configService.getAll();
    }

}
