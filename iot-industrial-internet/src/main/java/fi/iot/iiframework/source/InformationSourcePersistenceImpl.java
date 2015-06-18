/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.mutator.MarkReadoutAsErronousIfValueIs;
import fi.iot.iiframework.mutator.RemoveSensorIfNotActiveMutator;
import fi.iot.iiframework.mutator.ValueCondition;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.services.domain.SensorService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Responsible for writing read source-information to services and retrieving
 * information from services.
 */
@Service
public class InformationSourcePersistenceImpl implements InformationSourcePersistence {

    private final InformationSourceService sourceService;
    private final SensorService sensorService;

    @Autowired
    public InformationSourcePersistenceImpl(InformationSourceService sourceService, SensorService sensorService) {
        this.sourceService = sourceService;
        this.sensorService = sensorService;
    }

    @Override
    public List<InformationSource> loadSourcesFromDB() {
        return sourceService.getAll();
    }

    @Override
    @Transactional
    public InformationSource updateSensorsForSource(InformationSource source, List<Sensor> sensors) {
        final InformationSource src = sourceService.get(source.getId());
        sensors.forEach(s -> {
            s.setSource(src);
            src.getSensors().add(s);
        });
        addNewReadouts(src, sensors);
        mutateSensors(src);
        sourceService.save(src);
        return src;
    }

    /**
     * Adds new readouts to known sensors and saves those sensors with their new
     * readouts.
     *
     * @param source
     * @param sensors
     */
    private void addNewReadouts(InformationSource source, List<Sensor> sensors) {
        sensors.forEach(s -> {
            source.getSensors().forEach(se -> {
                if (s.equals(se)) {
                    s.getReadouts().forEach(r -> r.setSensor(se));
                    se.setReadouts(s.getReadouts());
                }
            });
        });
    }

    /**
     * Based on sensor-configuration, mark erronous readouts and disable
     * disabled sensors.
     *
     * @param sensors
     */
    private void mutateSensors(InformationSource source) {
        new RemoveSensorIfNotActiveMutator().mutateAll(source);
        new MarkReadoutAsErronousIfValueIs(ValueCondition.HIGHER_THAN).mutateAll(source);
        new MarkReadoutAsErronousIfValueIs(ValueCondition.LOWER_THAN).mutateAll(source);
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
