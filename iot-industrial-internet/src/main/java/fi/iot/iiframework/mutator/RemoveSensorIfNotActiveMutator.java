/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.mutator;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Sensor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class RemoveSensorIfNotActiveMutator implements Mutator {

    @Override
    public void mutateAll(InformationSource source) {
        Collection<Sensor> sensors = source.getSensors();
        sensors.removeIf(sensor -> !sensor.isActive());
        source.setSensors((Set<Sensor>) sensors);
    }

}
