/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.services.domain.SensorService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * Creates and manages objects that represent external data sources
 */
@Component
public class InformationSourceManagerImpl implements InformationSourceManager {

    private final Map<String, InformationSourceHandler> sources;

    @Autowired
    private InformationSourcePersistence persistence;

    public InformationSourceManagerImpl() {
        this.sources = new HashMap<>();
    }

    @PostConstruct
    public void loadConfigFromDB() {
        List<InformationSource> sources = persistence.loadSourcesFromDB();
        sources.forEach(c -> createSource(c));
    }

    @Override
    public void createSource(InformationSource config) {
        config = persistence.addSource(config);
        InformationSourceHandler source = new InformationSourceHandlerImpl(config, persistence);
        sources.put(config.getId(), source);
    }

    @Override
    public void removeSource(String id) {
        persistence.deleteSource(sources.get(id).getConfig());
        sources.get(id).close();
        sources.remove(id);
    }

    @Override
    public void updateSource(InformationSource config) {
        config = persistence.updateSource(config);
        sources.get(config.getId()).setConfig(config);
    }

    @Override
    public void readSource(String id) {
        InformationSourceHandler source = sources.get(id);
        source.readAndWrite();
    }

    /**
     * Returns map of sources.
     *
     * @return
     */
    public Map<String, InformationSourceHandler> getSources() {
        return sources;
    }

    /**
     * Sets the class for persistence.
     *
     * @param persistence
     */
    public void setPersistence(InformationSourcePersistence persistence) {
        this.persistence = persistence;
    }

}
