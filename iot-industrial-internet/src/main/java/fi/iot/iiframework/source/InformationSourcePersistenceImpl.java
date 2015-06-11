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
    public void updateSourceWithSensors(InformationSource source, List<Sensor> sensors) {
        List<Sensor> persistentSensors = sensorService.getBy(source);

        sensors.forEach(s -> s.setSource(source));
        
        sensors.forEach(s -> {
            if (!persistentSensors.contains(s)) {
                addNewSensor(source, s);
                return;
            }
            addReadoutsToPersistentSensors(persistentSensors, s);
        });

        sensorService.save(sensors);
    }

    private void addReadoutsToPersistentSensors(List<Sensor> persistentSensors, Sensor s) {
        persistentSensors.forEach(p -> {
            if (p.equals(s)) {
                p.setReadouts(s.getReadouts());
                sensorService.update(p);
            }
        });
    }

    /**
     * Makes a new sensor and it's possible readouts persistent.
     *
     * @param source
     * @param sensor
     */
    private void addNewSensor(InformationSource source, Sensor sensor) {
        sensorService.save(sensor);
    }

    @Override
    public void addSource(InformationSource source) {
        sourceService.save(source);
    }

    @Override
    public void updateSource(InformationSource source) {
        sourceService.save(source);
    }

    @Override
    public void deleteSource(InformationSource source) {
        sourceService.delete(source);
    }

}
