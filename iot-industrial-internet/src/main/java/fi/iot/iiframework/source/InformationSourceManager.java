/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * Creates and manages objects that represent external data sources
 */
@Service
public class InformationSourceManager {

    private final Map<Long, InformationSourceHandler> sources;

    @Autowired
    private InformationSourcePersistence persistence;

    public InformationSourceManager() {
        this.sources = new HashMap<>();
    }

    @PostConstruct
    public void loadConfigFromDB() {
        List<InformationSource> sources = persistence.loadSourcesFromDB();
        sources.forEach(c -> createSource(c));
    }

    public void createSource(InformationSource config) {
        config = persistence.addSource(config);
        InformationSourceHandler source = new InformationSourceHandlerImpl(config, persistence);
        sources.put(config.getId(), source);
    }

    public void removeSource(long id) {
        persistence.deleteSource(sources.get(id).getConfig());
        sources.get(id).close();
        sources.remove(id);
    }

    public void updateSource(InformationSource config) {
        config = persistence.updateSource(config);
        sources.get(config.getId()).setConfig(config);
    }

    @Async
    public void readSource(long id) {
        InformationSourceHandler source = sources.get(id);
        source.readAndWrite();
    }

    /**
     * Returns map of sources.
     *
     * @return
     */
    public Map<Long, InformationSourceHandler> getSources() {
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
