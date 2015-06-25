/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.source;

import java.util.List;
import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.mutator.MarkReadoutAsErronousIfValueIs;
import fi.iot.iiframework.mutator.ValueCondition;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.services.domain.ReadoutService;
import fi.iot.iiframework.services.domain.SensorService;
import java.util.Set;
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
    public InformationSource writeReadoutsToSource(InformationSource source, List<Sensor> sensors) {
        final InformationSource src = sourceService.getWithSensors(source.getId());
        addNewSensors(sensors, src);
        sourceService.save(src);
        associateReadoutsWithPersistentSensors(src, sensors);
        sourceService.save(src);
        return source;
    }

    private void addNewSensors(List<Sensor> sensors, final InformationSource src) {
        sensors.forEach(s -> {
            src.addSensor(s);
        });
    }

    /**
     * Adds new readouts to persistent sensors and saves those sensors with
     * their new readouts, cascading.
     *
     * @param source
     * @param sensors
     */
    private void associateReadoutsWithPersistentSensors(InformationSource source, List<Sensor> sensors) {
        sensors.forEach(s -> {
            source.getSensors().stream()
                    .filter(sensor -> sensor.isActive())
                    .forEach(sensor -> {
                        if (equalsButNotTheSameInstance(s, sensor)) {
                            addReadoutsToSensor(sensor, s.getReadouts());
                        }
                    });
        });
    }

    /**
     * No need to add readouts to the same object, which could be the case when
     * we first read an source.
     *
     * @param s
     * @param sensor
     * @return
     */
    private static boolean equalsButNotTheSameInstance(Sensor s, Sensor sensor) {
        return s.equals(sensor) && s != sensor;
    }

    /**
     * Get a sensor from database with its readouts, and add the new readouts.
     *
     * @param sen
     * @param readouts
     */
    private void addReadoutsToSensor(Sensor sen, Set<Readout> readouts) {
        Sensor sensor = sensorService.get(sen.getId());
        readouts.forEach(r -> {
            r.setSensor(sensor);
            mutateReadout(r);
        });
        readoutService.save(readouts);
    }

    /**
     * Based on sensor-configuration, mark erronous readouts.
     *
     * @param sensor
     */
    private void mutateReadout(Readout readout) {
        new MarkReadoutAsErronousIfValueIs(ValueCondition.HIGHER_THAN)
                .mutateReadout(readout);
        new MarkReadoutAsErronousIfValueIs(ValueCondition.LOWER_THAN)
                .mutateReadout(readout);
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
