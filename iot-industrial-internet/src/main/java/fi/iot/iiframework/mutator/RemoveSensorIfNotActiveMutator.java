/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.mutator;

import fi.iot.iiframework.domain.Sensor;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RemoveSensorIfNotActiveMutator implements Mutator {

    @Override
    public void mutateAll(List<Sensor> sensors) {
        sensors = sensors.stream().filter(p -> p.isActive()).collect(Collectors.toList());
    }

}
