/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.services.domain.SensorService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Responsible for writing read source-information to services and retrieving
 * information from services.
 */
@Component
public class InformationSourcePersistenceImpl implements InformationSourcePersistence {

    @Autowired
    private InformationSourceService sourceService;
    @Autowired
    private SensorService sensorService;

    @Override
    public List<InformationSource> loadSourcesFromDB() {
        return sourceService.getAll();
    }

    @Override
    @Transactional
    public void updateSourceWithSensors(InformationSource source, List<Sensor> sensors) {
        final InformationSource src = sourceService.get(source.getId());
        sensors.forEach(s -> {
            s.setSource(source);
            src.getSensors().add(s);
        });
        sourceService.save(src);

        addNewReadouts(src, sensors);
    }

    private void addNewReadouts(InformationSource source, List<Sensor> sensors) {
        sensors.forEach(s -> {
            source.getSensors().forEach(se -> {
                if (s.getName().equals(se.getName())){
                    se.setReadouts(s.getReadouts());
                    sensorService.save(se);
                }
            });
        });
    }

    @Override
    public InformationSource addSource(InformationSource source) {
        return sourceService.save(source);
    }

    @Override
    public InformationSource updateSource(InformationSource source) {
        return sourceService.save(source);
    }

    @Override
    public void deleteSource(InformationSource source) {
        sourceService.delete(source);
    }

}
