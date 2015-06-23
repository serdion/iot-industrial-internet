/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import java.util.List;
import javax.transaction.Transactional;
import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.mutator.MarkReadoutAsErronousIfValueIs;
import fi.iot.iiframework.mutator.ValueCondition;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.services.domain.ReadoutService;
import fi.iot.iiframework.services.domain.SensorService;
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
    private final ReadoutService readoutService;

    @Autowired
    public InformationSourcePersistenceImpl(
            InformationSourceService sourceService,
            SensorService sensorService,
            ReadoutService readoutService) {
        this.sourceService = sourceService;
        this.sensorService = sensorService;
        this.readoutService = readoutService;
    }

    @Override
    public List<InformationSource> loadSourcesFromDB() {
        return sourceService.getAll();
    }

    @Override
    @Transactional
    public InformationSource writeReadoutsToSource(InformationSource source, List<Sensor> sensors) {
        final InformationSource src = sourceService.get(source.getId());
        sensors.forEach(sensor -> src.addSensor(sensor));
        associateReadoutsWithPersistentSensors(src, sensors);
        sourceService.save(src);
        return source;
    }

    /**
     * Adds new readouts to known sensors and saves those sensors with their new
     * readouts.
     *
     * @param source
     * @param sensors
     */
    private void associateReadoutsWithPersistentSensors(InformationSource source, List<Sensor> sensors) {
        sensors.forEach(s -> {
            source.returnSensors().stream()
                    .filter(sensor -> sensor.isActive())
                    .forEach(sensor -> {
                        if (s.equals(sensor)) {
                            sensor.addReadouts(s.returnReadouts());
                            mutateReadouts(sensor);
                        }
                    });
        });
    }

    /**
     * Based on sensor-configuration, mark erronous readouts.
     *
     * @param sensor
     */
    private void mutateReadouts(Sensor sensor) {
        new MarkReadoutAsErronousIfValueIs(ValueCondition.HIGHER_THAN).mutateAll(sensor);
        new MarkReadoutAsErronousIfValueIs(ValueCondition.LOWER_THAN).mutateAll(sensor);
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
